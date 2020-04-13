package utils;

import entities.*;
import enums.TrainingPartEnum;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

@Slf4j
public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(CalValuesEntity.class);
                configuration.addAnnotatedClass(ExerciseEntity.class);
                configuration.addAnnotatedClass(ExerciseTypeEntity.class);
                configuration.addAnnotatedClass(MetterValuesEntity.class);
                configuration.addAnnotatedClass(RepValuesEntity.class);
                configuration.addAnnotatedClass(SecValuesEntity.class);
                configuration.addAnnotatedClass(TrainingPartEntity.class);
                configuration.addAnnotatedClass(ValuesEntity.class);
                configuration.addAnnotatedClass(MeasurementUnitEntity.class);
                StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(standardServiceRegistryBuilder.build());
            } catch (Exception e) {
                log.error("Ошибка при попытке создания фабрики сессий hibernate", e);
            }
        }
        return sessionFactory;
    }
}
