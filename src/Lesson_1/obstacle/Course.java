package Lesson_1.obstacle;

import Lesson_1.Team;

public class Course {
    private Obstacle[] obstacles;

    public Course(Obstacle ...obstacles) {
        this.obstacles = obstacles;
    }

    public void doIt(Team team) {
        team.doIt(obstacles);
    }
}
