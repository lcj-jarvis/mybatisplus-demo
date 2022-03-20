package generate;

import generate.Teacher;

/**
 * @Entity generate.Teacher
 */
public interface TeacherDao {
    /**
     *
     * @mbg.generated 2021-03-04 15:36:42
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 15:36:42
     */
    int insert(Teacher record);

    /**
     *
     * @mbg.generated 2021-03-04 15:36:42
     */
    int insertSelective(Teacher record);

    /**
     *
     * @mbg.generated 2021-03-04 15:36:42
     */
    Teacher selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 15:36:42
     */
    int updateByPrimaryKeySelective(Teacher record);

    /**
     *
     * @mbg.generated 2021-03-04 15:36:42
     */
    int updateByPrimaryKey(Teacher record);
}