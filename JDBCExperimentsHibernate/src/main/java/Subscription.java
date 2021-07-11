import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Subscriptions")
public class Subscription {
    @EmbeddedId
    private SubscriptionPK id;
    @Column (name = "subscription_date")
    private Date subscriptionDate;
    @MapsId("studentId")
    @ManyToOne
    private Student student;
    @MapsId("courseId")
    @ManyToOne
    private Course course;

    public Subscription() {
    }

    public Subscription(SubscriptionPK id) {
        this.id = id;
    }

    public SubscriptionPK getId() {
        return id;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setId(SubscriptionPK id) {
        this.id = id;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscription )) return false;
        return id != null && id.equals(((Subscription) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
