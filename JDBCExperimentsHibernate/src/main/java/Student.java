import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table( name = "Students")
public class Student {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    @Column(name = "registration_date")
    private Date registrationDate;

    @ManyToMany (mappedBy = "students")
    private List<Course> courses;

    @OneToMany (mappedBy = "student",cascade = CascadeType.ALL)
    private List<Subscription> subscriptions;


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void addSubscription(Subscription subscription) {
        subscriptions.add(subscription);
        subscription.setStudent(this);
    }

    public void removeSubscription(Subscription subscription) {
        subscriptions.remove(subscription);
        subscription.setStudent(null);
    }
}
