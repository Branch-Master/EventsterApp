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
        User birgir = new User(new Long(11),"birgir", "birgir","27.06","8665825","cancer","boo11@hi.is",false);
        User adam = new User(new Long(12),"adam", "adam","10.02","8673425","aquarious","ajj10@hi.is",false);
        User marino = new User(new Long(13),"marino", "marino","22.05","8459701","twinsies","mak78@hi.is",false);
        User isak = new User(new Long(14),"isak", "isak","08.12","6954725","sagittarius","iss23@hi.is",false);

        Group vinir = new Group("Vinirnir","bara vinirnir",new Long(21),false);
        vinir.setMembers(new ArrayList<User>(Arrays.asList(birgir, adam,marino,isak)));
        Group aular = new Group("Aularnir","fokking aular",new Long(22),true);
        aular.setMembers(new ArrayList<User>(Arrays.asList(marino, adam,isak)));

        Group eldri = new Group("Eldriborgarar","Ellismellirnir adam og birgir",new Long(23),false);
        eldri.setMembers(new ArrayList<User>(Arrays.asList(birgir, adam,isak)));

        Comment skitaComment = new Comment(new Long(31),new Long(42),"ertu að grínast eða",new Long(13));
        Comment helladComment = new Comment(new Long(32),new Long(41),"vá hvað ég er spenntur",new Long(11));

        Event afmaeli = new Event(new Long(41),"AAAAMLI","Afmælis partý hjá Marinó maður",new Long(21),"party","22.05","23.05","Grafarvoginum hjá marino",3,false);
        afmaeli.setGoing(new ArrayList<User>(Arrays.asList(birgir, adam, marino)));
        afmaeli.setComments(new ArrayList<Comment>(Arrays.asList(helladComment)));

        Event aulahittingur = new Event(new Long(42),"Aulahittingur","Allir aularnir spenntir fyrir hittingum!?",new Long(22),"party","05.05","07.05","B5",2,true);
        aulahittingur.setGoing(new ArrayList<User>(Arrays.asList(adam)));
        aulahittingur.setComments(new ArrayList<Comment>(Arrays.asList(skitaComment)));

        Event fobo = new Event(new Long(43),"Fóbó","Nördabolti fyrir alla",new Long(23),"sports","25.03","25.03","Ásgarði",10,true);

        events = new ArrayList<Event>(Arrays.asList(afmaeli,aulahittingur,fobo));
        users = new ArrayList<User>(Arrays.asList(birgir,adam,marino,isak));
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

    public Event getEventById(Long id){
        for(Event e : getEvents()){
            if(e.getId().equals(id)){
                return e;
            }
        }
        return new Event(new Long(-1),"Ekkert fannst","Engar upplýsingar",new Long(-1),"ekkert","25.03","25.03","Ekkert",10,false);
    }

    public Group getGroupById(Long id){
        for(Group g : getGroups()){
            if(g.getId().equals(id)){
                return g;
            }
        }
        return new Group("ekkert fannst","Engar upplýsingar",new Long(-1),false);
    }

    public User getUserById(long id){
        for(User u : getUsers()){
            if(u.getId().equals(id)){
                return u;
            }
        }
        return new User(new Long(-1),"fannstEkki", "ekki","27.06","ekkert","ekkert","ekkert@hi.is",false);
    }


}
