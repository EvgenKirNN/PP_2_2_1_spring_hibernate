package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      userService.add(new User("Петр", "Петров", "Petr_Petrov@mail.ru", new Car("Gaz", 3310)));
      userService.add(new User("Степан", "Степанов", "Stepan_Stepanov@mail.ru", new Car("Moskvitch", 412)));

      User user1 = new User("Иван", "Иванов", "Ivan_Ivanov@mail.ru");
      Car car1 = new Car("Lada", 2103);
      user1.setUserCar(car1);
      userService.add(user1);

      List<User> users = userService.listUsers();
      users.forEach(System.out::println);

      System.out.println(userService.getCarOwner("Lada", 2103));
      System.out.println(userService.getCarOwner("Moskvitch", 412));

//      for (User user : users) {
//         System.out.println("Id = "+user.getId());
//         System.out.println("First Name = "+user.getFirstName());
//         System.out.println("Last Name = "+user.getLastName());
//         System.out.println("Email = "+user.getEmail());
//         System.out.println();
//      }

      context.close();
   }
}