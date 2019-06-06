package Lesson_1;

import Lesson_1.animal.Cat;
import Lesson_1.animal.Dog;
import Lesson_1.human.Human;
import Lesson_1.obstacle.Course;
import Lesson_1.obstacle.Cross;
import Lesson_1.obstacle.Wall;

public class Main {
    public static void main(String[] args) {
        final Course course = new Course(new Cross(80), new Wall(25), new Wall(1),
                new Cross(120));
        final Team team = new Team(new Human("Боб"), new Cat("Барсик"), new Dog("Бобик"));

        course.doIt(team);
        team.showInfoAllMembers();
        team.showInfoForIsOnDistance();
    }
}