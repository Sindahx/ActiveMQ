package com.tgb.SpringActivemq.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/22.
 */
@Repository
public class ApplyDao {

    /**
     * JDBC
     */
    @Resource(name = "JdbcTemplate")
    protected JdbcTemplate jdbc;

    /**
     * 获取病历信息
     *
     * @param simpleId 病历Id
     * @return 病历信息
     */
    public Map<String, Object> getSimpleInfo(String simpleId) {
        String sql = "SELECT HS.ID, HS.USERID, UI.USERNAME, HS.NAME, HS.PATIENTNAME, HS.SEX, HS.AGE, HS.CUREDOCTOR, HS.INSNO, HS.DEPTCODE, HS.SIMPLE_TYPE, " +
                "HS.DEPTNAME, HS.BEDNO, HS.INDATE, HS.LASTUPDATE, HS.HAND, HS.TELL,HS.CHIEF_COMPLAINT,HS.INITIAL_DIAGNOSIS, HS.PACS_DATA AS pacsData, HS.CREATE_USERID," +
                "HS.PACS_IMG_DATA, HS.PACS_SELECT_DETAILS as pacsDetails, datediff(now(),HS.INDATE) AS INDAY, HS.KEYWORDS, HS.JSON_CONTENT, HS.EHRID, HS.CREATEDATE ,HS.KS_SIMPLE,HS.QY_SIMPLE,UI.ORG_CODE  ORG_CODE,wordNO wordNO " +
                "FROM HZ_SIMPLE HS, hz_user_info UI WHERE HS.CREATE_USERID = UI.UUID AND HS.ID = '" + simpleId + "'";
        try {
            return jdbc.queryForMap(sql);
        } catch (Exception e) {
            return null;
        }
    }
}
