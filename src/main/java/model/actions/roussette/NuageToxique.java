package model.actions.roussette;

import model.Animal;
import model.actions.DebuffAction;

public class NuageToxique extends DebuffAction {
    public NuageToxique() { super("Nuage Toxique"); }
    @Override
    public void executer(Animal user, Animal target) {
        if (Math.random() * 100 > 85) {
            System.out.println(user.getNom() + " rate Nuage Toxique !");
            return;
        }
        target.applyStatusPoison();
        System.out.println(target.getNom() + " est empoisonné par Nuage Toxique !");
    }
}
