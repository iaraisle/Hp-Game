package ar.com.ada.online.second.hpgame.character;

import ar.com.ada.online.second.hpgame.Gameboard;
import ar.com.ada.online.second.hpgame.spell.Attack;
import ar.com.ada.online.second.hpgame.spell.Defense;
import ar.com.ada.online.second.hpgame.spell.Recovery;

import java.util.List;
import java.util.Scanner;

public class Elf extends Character {
    private static Scanner keyboard = new Scanner(System.in);

    public Elf() {
    }

    @Override
    public void attack(Character opponent) {
        int damageEnergySum = 0;
        String attackLocation = null;
        System.out.println("Los hechizos de ataque disponibles son: " + getAttackSpellList());

        List<Attack> attackSpellList = getAttackSpellList();
        for (int i = 0; i < attackSpellList.size(); i++) {
            System.out.println((i + 1) + ") " + attackSpellList.get(i).getName());
        }
        // pedir que hechizo usar (1-n) opt
        int opt;
        System.out.println("Elije tu ataque preferido: ");
        opt = keyboard.nextInt();
        Attack attack = attackSpellList.get(opt - 1);

        if (getMagicLevel() > attack.getMagicEnergy()) {
            if (isDarkOrFree()) {
                damageEnergySum = damageEnergySum + 5 + attack.getDamageEnergy();

            } else
                damageEnergySum = damageEnergySum + attack.getDamageEnergy();

            magicLevel = magicLevel - attack.getMagicEnergy();
            // preguntar la ubicacion del ataque
            attackLocation = Gameboard.selectLocation();
            opponent.reciveAttack(damageEnergySum, attackLocation);

        } else {
            System.out.println("No tenés suficiente energía para realizar este hechizo, te regalamos 10 puntos para el próximo turno");
            int giftMagicLevel = this.getMagicLevel() + 10;
            this.setMagicLevel(giftMagicLevel);
        }


    }


    @Override
    public void defense() {
        int defenseSum = 0;
        System.out.println("Los hechizos de protección disponibles son: " + getDefenseSpellList());
        List<Defense> defenseSpellListList = getDefenseSpellList();
        for (int i = 0; i < defenseSpellListList.size(); i++) {
            System.out.println((i + 1) + ") " + defenseSpellListList.get(i).getName());
        }
        // pedir que hechizo usar (1-n) opt
        int opt;
        System.out.println("Elije tu hechizo de protección: ");
        opt = keyboard.nextInt();
        Defense defense = defenseSpellListList.get(opt - 1);


        if (getMagicLevel() > defense.getMagicEnergy()) {
            if (isDarkOrFree()) {
                defenseSum = defenseSum + 5 + defense.getDefenseEnergy();

            } else
                defenseSum = defenseSum + 10 + defense.getDefenseEnergy();

            magicLevel = magicLevel - defense.getMagicEnergy();
            energyLevel = energyLevel + defenseSum;
        } else {
            System.out.println("No tenés suficiente energía para realizar este hechizo, te regalamos 10 puntos para el próximo turno");
            int giftMagicLevel = this.getMagicLevel() + 10;
            this.setMagicLevel(giftMagicLevel);

        }


    }


    @Override
    public void magicRecovery() {
        int magicRecoverySum = 0;
        System.out.println("Los hechizos de recuperación mágica disponibles son: " + getRecoverySpellList());
        List<Recovery> magicRecoverySpellList = getRecoverySpellList();
        for (int i = 0; i < magicRecoverySpellList.size(); i++) {
            System.out.println((i + 1) + ") " + magicRecoverySpellList.get(i).getName());
        }
        // pedir que hechizo usar (1-n) opt
        int opt;
        System.out.println("Elije tu hechizo de protección: ");
        opt = keyboard.nextInt();
        Recovery recovery = magicRecoverySpellList.get(opt - 1);

        if (getMagicLevel() > recovery.getMagicEnergy()) {
            if (isDarkOrFree()) {
                magicRecoverySum = magicRecoverySum + 5 + recovery.getEnergyRecovery();

            } else
                magicRecoverySum = magicRecoverySum + recovery.getEnergyRecovery();

            magicLevel = magicLevel + magicRecoverySum - recovery.getMagicEnergy();
        } else {
            System.out.println("No tenés suficiente energía para realizar este hechizo, te regalamos 10 puntos para el próximo turno");
            int giftMagicLevel = this.getMagicLevel() + 10;
            this.setMagicLevel(giftMagicLevel);

        }

    }

    @Override
    public boolean isDead() {
        Boolean hasDead = false;
        if (energyLevel <= 0)
            hasDead = true;

        return hasDead;
    }

    @Override
    public String characterStatus() {
        return "Estado del personaje" +
                "\n Nivel de vida: " + getEnergyLevel() +
                "\n Ubicación: " + getLocation() +
                "\n Energía mágica: " + getMagicLevel() + "\n";

    }
}
