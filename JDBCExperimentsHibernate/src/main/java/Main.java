import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Course course1 = session.get(Course.class, 1);
        List<Student> students = course1.getStudents();
        List<Subscription> subscriptions = course1.getSubscriptions();
        System.out.println(students.size());
        students.forEach(student -> System.out.println(student.getName()));
        subscriptions.forEach(subscription -> System.out.println(subscription.getSubscriptionDate()));
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query .select(root);
        Query<Course> q = session.createQuery(query);
        List<Course> courses = q.getResultList();
        int i = 1;
        Integer count;
        for (Course course : courses){
            count = course.getStudentsCount();
            if (count == null) {
                count = 0;
            }
            System.out.println(i + ". " + course.getName() + " - " + count);
            ++i;
        }
        String hql_link_del = "delete LinkedPurchaseList";
        String hql_student = "from Student where name = :paramName";
        String hql_course = "from Course where name = :paramName";
        session.createQuery(hql_link_del).executeUpdate();
        SQLQuery query_sql = session.createSQLQuery("select * from purchaselist");
        List<Object[]> rows = query_sql.list();
        /* Второй способ получения id, чтобы не формировать запросы к бд каждый раз
        String hql_student = "from Student";
        String hql_course = "from Course";
        List<Student> listStudents = session.createQuery(hql_student).getResultList();
        List<Course> listCourses = session.createQuery(hql_course).getResultList();*/
        ArrayList<LinkedPurchaseList> linkedPurchaseLists = new ArrayList<LinkedPurchaseList>();
        rows.forEach(row -> {
            String student_name = row[0].toString();
            String course_name = row[1].toString();
            Integer price = Integer.parseInt(row[2].toString());
            Date subscription = (Date) row[3];
            int student_id = 0;
            int course_id = 0;
            List<Student> listStudents = session.createQuery(hql_student).setParameter("paramName", student_name).getResultList();
            student_id = listStudents.get(0).getId();
            List<Course> listCourses = session.createQuery(hql_course).setParameter("paramName", course_name).getResultList();
            course_id = listCourses.get(0).getId();
            /* Второй способ получения id, чтобы не формировать запросы к бд каждый раз
            for (Course listCourse : listCourses) {
                if (listCourse.getName().compareTo(course_name) == 0){
                    course_id = listCourse.getId();
                }
            }
            for (Student listStudent : listStudents) {
                if (listStudent.getName().compareTo(student_name) == 0){
                    student_id = listStudent.getId();
                }
            }*/
            LinkedPurchaseListPK pk = new LinkedPurchaseListPK(student_id,course_id);
            linkedPurchaseLists.add(new LinkedPurchaseList(pk, student_name, course_name, price, subscription));
        });
        linkedPurchaseLists.forEach(linkedPurchaseList -> session.save(linkedPurchaseList));
        transaction.commit();
        sessionFactory.close();
    }
}
