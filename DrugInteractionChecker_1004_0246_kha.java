// 代码生成时间: 2025-10-04 02:46:24
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;
import java.util.Properties;

// DrugInteractionChecker.java
// This class uses MyBatis to interact with the database and check for drug interactions.

@Mapper
public interface DrugInteractionMapper {
    // Select statement to retrieve drug interactions based on drug IDs
    @Select("SELECT * FROM drug_interactions WHERE drug_id_1 = #{drugId1} AND drug_id_2 = #{drugId2}")
    List<DrugInteraction> getInteractions(@Param("drugId1") int drugId1, @Param("drugId2") int drugId2);
}

public class DrugInteractionChecker {
    private SqlSessionFactory sqlSessionFactory;

    // Constructor to initialize the SqlSessionFactory
    public DrugInteractionChecker(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    // Method to check for drug interactions between two drugs
    public List<DrugInteraction> checkDrugInteractions(int drugId1, int drugId2) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DrugInteractionMapper mapper = session.getMapper(DrugInteractionMapper.class);
            return mapper.getInteractions(drugId1, drugId2);
        } catch (Exception e) {
            // Handle any exceptions that occur during the database interaction
            e.printStackTrace();
            return null;
        }
    }
}

// DrugInteraction.java
// This class represents a drug interaction record.
public class DrugInteraction {
    private int drugId1;
    private int drugId2;
    private String interactionType;
    private String severity;

    // Getters and setters for the class properties
    public int getDrugId1() { return drugId1; }
    public void setDrugId1(int drugId1) { this.drugId1 = drugId1; }
    public int getDrugId2() { return drugId2; }
    public void setDrugId2(int drugId2) { this.drugId2 = drugId2; }
    public String getInteractionType() { return interactionType; }
    public void setInteractionType(String interactionType) { this.interactionType = interactionType; }
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    // Overriding toString method for easy printing of drug interaction details
    @Override
    public String toString() {
        return "DrugInteraction{"
                + "drugId1=" + drugId1
                + ", drugId2=" + drugId2
                + ", interactionType='" + interactionType + '"
                + ", severity='" + severity + '"'
                + '}';
    }
}
