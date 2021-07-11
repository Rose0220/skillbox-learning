import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Linked_purchase_list")
public class LinkedPurchaseList {
    @EmbeddedId
    private LinkedPurchaseListPK id;
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "price")
    private Integer price;
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public LinkedPurchaseList() { }

    public LinkedPurchaseList(LinkedPurchaseListPK id) {
        this.id = id;
    }

    public LinkedPurchaseList(LinkedPurchaseListPK id, String studentName, String courseName, int price, Date subscriptionDate) {
        this.id = id;
        this.studentName = studentName;
        this.courseName = courseName;
        this.price = price;
        this.subscriptionDate = subscriptionDate;
    }

    public LinkedPurchaseListPK getId() { return id; }

    public void setId(LinkedPurchaseListPK id) { this.id = id; }

    public void setStudentName(String studentName) { this.studentName = studentName; }

    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getStudentName() { return studentName; }

    public String getCourseName() { return courseName; }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) { this.price = price; }

    public Date getSubscription() { return subscriptionDate; }

    public void setSubscription(Date subscription) { this.subscriptionDate = subscription; }
}
