// 代码生成时间: 2025-10-11 20:32:33
 * using MyBatis framework for database interaction.
 */
package com.example.dataanalysis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.Reader;
import java.util.List;
import java.util.Properties;

public class DataAnalysisService {

    private SqlSessionFactory sqlSessionFactory;

    // Constructor to initialize the SqlSessionFactory
    public DataAnalysisService(Reader reader) {
        try {
            Properties props = new Properties();
            props.setProperty("mybatis.environment.id", "development");
            props.setProperty("mybatis.environment.default-transaction-isolation", "READ_COMMITTED");
            props.setProperty("mybatis.mapper-locations", "classpath:mapper/*.xml");
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "mybatis-config", props);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SqlSessionFactory", e);
        }
    }

    // Method to get total count of entries
    public int getTotalCount() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("com.example.dataanalysis.mapper.DataMapper.getTotalCount");
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving the total count", e);
        }
    }

    // Method to get top N entries
    public List<?> getTopNEntries(int limit) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("com.example.dataanalysis.mapper.DataMapper.getTopNEntries", limit);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving top N entries", e);
        }
    }

    // Close the SqlSessionFactory
    public void close() {
        this.sqlSessionFactory.close();
    }
}
