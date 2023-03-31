package utils;

import entities.*;
import entities.attributes_links.ItemPic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class hibernate_factory {
    public static Session session;
    public static SessionFactory factory;
    public static void sessionInit() {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Car.class).addAnnotatedClass(CarAttribute.class).
                        addAnnotatedClass(Item.class).addAnnotatedClass(ItemAttribute.class).
                        addAnnotatedClass(Fitment.class).addAnnotatedClass(FitmentAttribute.class).
                        addAnnotatedClass(ItemPic.class).
                        buildSessionFactory();
        session = factory.getCurrentSession();
    }
}
