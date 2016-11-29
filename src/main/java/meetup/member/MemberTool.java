package meetup.member;

import com.alibaba.fastjson.JSON;
import meetup.group.Group;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import util.Constants;
import util.HttpUtil;
import util.PojoID;
import util.UriBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by zhaokai on 16-11-18.
 */
public class MemberTool {

    private static Logger logger=Logger.getLogger(MemberTool.class);

    public static List<Member> getMembersOfAGroupFromHttp(Group group) throws URISyntaxException, IOException {
        URIBuilder uriBuilder= UriBuilder.get();
        uriBuilder.setPath("/2/members")
                .addParameter("group_id", group.getGroupId());

        URI uri=uriBuilder.build();

        logger.info("获取member："+uri.toString());

        String membersJsonStr= JSON.parseObject(HttpUtil.getEntityString(uri)).getString("results");
        List<Member> members=JSON.parseArray(membersJsonStr,Member.class);

        logger.info("获取"+members.size()+"个member。");

        return members;
    }

    public static void storeMembers(List<Member> members, MongoTemplate mongoTemplate){
        for(Member member:members){
            mongoTemplate.remove(query(where(PojoID.MEMBER_ID).is(member.getMemberId())),Member.class);
            mongoTemplate.insert(member);
            logger.info("插入member:"+member);
        }
    }

    public static void getAndStoreMembersInNewYork(MongoTemplate mongoTemplate) throws IOException, URISyntaxException {
        List<Group> groupList=mongoTemplate.findAll(Group.class);
        for(Group group:groupList){
            try {
                List<Member> members = getMembersOfAGroupFromHttp(group);

                if (members.isEmpty()) {//该group为私密或者没有成员，则删除之
                    mongoTemplate.remove(query(where(PojoID.GROUP_ID).is(group.getGroupId())), Group.class);
                } else {//该group有成员
                    for (Member member : members) {
                        //为数据库中group添加该member
                        mongoTemplate.updateFirst(query(where(PojoID.GROUP_ID).is(group.getGroupId())),
                                new Update().push("members", member.getMemberId()), Group.class);

                        //为数据库中的member添加group
                        if (mongoTemplate.exists(query(where(PojoID.MEMBER_ID).
                                is(member.getMemberId())), Member.class)) {
                            mongoTemplate.updateFirst(query(where(PojoID.MEMBER_ID).
                                    is(member.getMemberId())), new Update().push("groups", group), Member.class);
                        } else {
                            member.setGroups(Arrays.asList(group.getGroupId()));
                            mongoTemplate.insert(member);
                        }
                    }
                }
                Thread.sleep(5 * Constants.SECOND);
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }

        }
    }



}
