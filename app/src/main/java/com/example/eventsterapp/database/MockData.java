package com.example.eventsterapp.database;

import com.example.eventsterapp.models.Comment;
import com.example.eventsterapp.models.Event;
import com.example.eventsterapp.models.Group;
import com.example.eventsterapp.models.User;

import java.util.ArrayList;
import java.util.Arrays;

public class MockData {

    ArrayList<Event> events;
    ArrayList<Comment> comments;
    ArrayList<User> users;
    ArrayList<Group> groups;

    public MockData() {
        User birgir = new User(new Long(1),"birgir", "birgir","27.06","8665825","cancer","boo11@hi.is",false);
        User adam = new User(new Long(2),"adam", "adam","10.02","8673425","aquarious","ajj10@hi.is",false);
        User marino = new User(new Long(3),",marino", "marino","22.05","8459701","twinsies","mak78@hi.is",false);
        Group vinir = new Group("Vinirnir","bara vinirnir",new Long(1),false);
        vinir.setMembers(new ArrayList<User>(Arrays.asList(birgir, adam)));
        Group aular = new Group("Aularnir","fokking aular",new Long(2),true);
        aular.setMembers(new ArrayList<User>(Arrays.asList(marino, adam)));
        Group eldri = new Group("Eldriborgarar","Ellismellirnir adam og birgir",new Long(3),false);
        eldri.setMembers(new ArrayList<User>(Arrays.asList(birgir, adam)));
        Comment skitaComment = new Comment(new Long(1),new Long(2),"ertu að grínast eða",new Long(3));
        Comment helladComment = new Comment(new Long(2),new Long(1),"vá hvað ég er spenntur",new Long(1))
        Event afmaeli = new Event(new Long(1),"AAAAMLI","Afmælis partý hjá Marinó maður",new Long(1),"party","22.05","23.05","Grafarvoginum hjá marino",3,false);
        afmaeli.setGoing(new ArrayList<User>(Arrays.asList(birgir, adam, marino)));
        afmaeli.setComments(new ArrayList<Comment>(Arrays.asList(helladComment)));
        Event aulahittingur = new Event(new Long(2),"Aulahittingur","Allir aularnir spenntir fyrir hittingum!?",new Long(2),"party","05.05","07.05","B5",2,true);
        aulahittingur.setGoing(new ArrayList<User>(Arrays.asList(adam)));
        aulahittingur.setComments(new ArrayList<Comment>(Arrays.asList(skitaComment)));
        Event fobo = new Event(new Long(2),"Fóbó","Nördabolti fyrir alla",new Long(0),"sports","25.03","25.03","Ásgarði",10,true);

        events = new ArrayList<Event>(Arrays.asList(afmaeli,aulahittingur,fobo));
        users = new ArrayList<User>(Arrays.asList(birgir,adam,marino));
        comments = new ArrayList<Comment>(Arrays.asList(skitaComment,helladComment));
        groups = new ArrayList<Group>(Arrays.asList(vinir,aular,eldri));
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }
}
