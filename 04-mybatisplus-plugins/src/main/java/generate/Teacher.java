package generate;

import java.io.Serializable;

/**
 * null
 * @TableName t_teacher
 *
 * 使用mybatisx插件生成的
 */
public class Teacher implements Serializable {
    /**
     * 
     *
     * @mbg.generated 2021-03-04 15:36:42
     */
    private Integer id;

    /**
     * 
     *
     * @mbg.generated 2021-03-04 15:36:42
     */
    private String name;

    /**
     * 
     *
     * @mbg.generated 2021-03-04 15:36:42
     */
    private Integer age;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_teacher
     *
     * @mbg.generated 2021-03-04 15:36:42
     */
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}