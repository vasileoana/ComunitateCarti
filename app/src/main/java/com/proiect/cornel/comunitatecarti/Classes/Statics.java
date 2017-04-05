package com.proiect.cornel.comunitatecarti.Classes;

import java.util.ArrayList;
import java.util.List;

public class Statics {

    public static User userCurent = null;
    public static List<User> useri = new ArrayList<>();
    public static List<Carte> listaMeaCarti = new ArrayList<>();
    public static List<Carte> listaTotalaCarti = new ArrayList<>();

    public static void initCartiUser() {
        listaMeaCarti = new ArrayList<>();
        if (userCurent != null) {
            for (Carte carte : listaTotalaCarti) {
                if (carte.getIdUtilizator() == userCurent.getId()) {
                    listaMeaCarti.add(carte);
                }
            }
        } else {
            //initializare in cazul in care sunt probleme cu serveru; / nu exista cont
            listaMeaCarti.add(new Carte(1, "Ion", "Liviu Rebreanu", "roman", 4.2, true, 0));
            listaMeaCarti.add(new Carte(2, "Amintiri din copilarie", "Ion Creanga", "povestiri", 4.2, true, 0));
            listaMeaCarti.add(new Carte(3, "Hunger games", "Suzanne Collins", "roman", 4.2, true, 0));
            listaMeaCarti.add(new Carte(4, "Ultima noapte de dragoste, intaia noapte de razboi", "Camil Petrescu", "bildungesroman", 4.2, true, 0));
        }
    }

    public static boolean verificaUser(String username, String parola) {
        for (User user : Statics.useri) {
            if (user.getUsername().equals(username) && user.getParola().equals(parola)) {
                Statics.userCurent = new User(user.getId(), user.getNume(), user.getPrenume(),
                        user.getUsername(), user.getParola(), user.getEmail(), user.getTelefon(), user.getSex(),
                        user.getDataNasterii());
                return true;
            }
        }
        return false;
    }

    public static String getNrTelefonEmail(int idUtilizator) {
        String ret = "Nu exista numar de telefon sau email";
        for (User user : useri) {
            if (user.getId() == idUtilizator) {
                ret = user.getTelefon() + "; " + user.getEmail();
            }
        }
        return ret;
    }

}
