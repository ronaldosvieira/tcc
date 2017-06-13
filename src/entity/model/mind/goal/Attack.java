package entity.model.mind.goal;

import entity.model.Animal;
import entity.model.LivingThing;
import entity.model.mind.sense.Perception;
import org.joml.Vector2f;

import java.util.Random;

public class Attack extends Goal {
    public Attack(Animal animal) {
        super(animal);
    }

    @Override
    public void cycle() {
        getAnimal().setMovementSpeed(0);

        System.out.println(getAnimal().getName() + " attack - " + perception.get("timestamp", Long.class));

        ((LivingThing) perception.getSource()).attack();

        direction = perception
                .get("distance", Vector2f.class)
                .normalize(new Vector2f());
    }

    @Override
    public boolean check() {
        return perception.get("is-eatable", Boolean.class);
    }
}
