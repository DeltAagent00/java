package Lesson_1;

import Lesson_1.obstacle.Obstacle;

public class Team {
    private Competitor[] members;

    public Team(Competitor ...members) {
        this.members = members;
    }

    public void doIt(Obstacle[] obstacles) {
        if (obstacles != null && obstacles.length > 0) {
            for(Obstacle obstacle : obstacles) {
                if (obstacle != null) {
                    for (Competitor competitor : members) {
                        if (competitor != null && competitor.isOnDistance()) {
                            obstacle.doIt(competitor);
                        }
                    }
                }
            }
        }
    }

    public void showInfoAllMembers() {
        for(Competitor competitor : members) {
            competitor.info();
        }
    }

    public void showInfoForIsOnDistance() {
        for(Competitor competitor : members) {
            if (competitor.isOnDistance()) {
                competitor.info();
            }
        }
    }
}
