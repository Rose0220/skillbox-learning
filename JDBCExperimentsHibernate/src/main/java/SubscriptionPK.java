import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubscriptionPK implements Serializable {
    @Column(name = "student_id")
    private int studentId;
    @Column(name = "course_id")
    private int courseId;

    public SubscriptionPK() {
    }

    public SubscriptionPK(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public boolean equals(SubscriptionPK o) {
        if (o.studentId == this.studentId && o.courseId == this.courseId)
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}
