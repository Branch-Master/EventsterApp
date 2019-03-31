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

        Group vinir = new Group("Vinirnir","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum et ultricies dui. Cras commodo sem sed justo bibendum, nec placerat dui commodo. Nunc id consectetur odio. Maecenas ut tristique lacus. Aliquam semper blandit felis non ultricies. Suspendisse sit amet felis nec justo ornare vehicula eget in tellus. Quisque convallis sed ex ut ultrices. Duis luctus porttitor augue, at venenatis nulla dictum id. Quisque eu molestie nulla, sit amet tincidunt turpis. Curabitur volutpat, lacus dapibus imperdiet mollis, leo erat auctor metus, a molestie justo nunc ut eros. Integer nec tristique massa. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Quisque pretium diam ac tellus pellentesque, non mattis arcu tempor.\n" +
                "\n" +
                "Suspendisse vel sapien efficitur, pellentesque eros ut, dignissim nisl. Pellentesque ullamcorper a mauris non dictum. Donec a magna nec lorem tincidunt aliquam. Ut id tincidunt turpis, nec egestas sem. In vulputate dui gravida nisi dignissim sodales. Cras congue leo eget consequat fringilla. Morbi viverra augue in nisi tristique molestie nec at dui. Cras eu odio et ligula posuere ultricies non nec lacus. Duis luctus neque id ipsum facilisis fringilla. Ut at nisi vel lorem tincidunt mattis ac et massa. Ut semper faucibus nisl ut accumsan. Mauris nec tempus justo. Sed eu nibh diam. Integer sed semper est, id varius tortor. Donec et dignissim diam, sit amet imperdiet libero. Etiam eget felis hendrerit, consequat nunc eget, dapibus lectus.\n" +
                "\n" +
                "Quisque non fermentum justo. Phasellus pulvinar ante sed leo viverra blandit. Maecenas vel pharetra nibh. Nullam nec massa tincidunt, tempor dui id, bibendum lorem. In ac facilisis nunc. Nullam quis rutrum risus. In hac habitasse platea dictumst. Praesent dictum erat id lectus commodo tincidunt. Aenean quam ante, mattis a erat vitae, viverra fringilla sapien. Integer id venenatis tellus. Phasellus massa felis, tincidunt quis iaculis quis, sagittis id nulla. Fusce non blandit ex, ut porttitor justo. Morbi sit amet ultricies orci. Donec metus odio, bibendum sed rutrum vitae, aliquet a sapien. Aenean urna est, placerat nec porta et, molestie eu enim.\n" +
                "\n" +
                "Etiam egestas sagittis ipsum. Nam facilisis gravida ante quis commodo. Quisque in urna vel sapien sodales iaculis. In non felis metus. Fusce vel blandit odio. Quisque pulvinar eros est, non ultricies mauris fermentum id. Nam id fermentum arcu. Nunc tortor felis, semper et luctus sed, ornare fringilla sem. Nulla facilisi. Mauris eget quam quis nunc ornare luctus. Fusce vitae blandit est, ac tincidunt orci. Integer diam massa, ullamcorper vitae tortor non, feugiat condimentum sapien. Morbi finibus lorem nec ligula iaculis molestie. In hac habitasse platea dictumst. Curabitur malesuada, diam sed pulvinar blandit, justo urna varius odio, pharetra sollicitudin nisi risus vel eros.\n" +
                "\n" +
                "Aliquam ex neque, luctus sodales eros eget, vestibulum tristique risus. Pellentesque auctor quam magna, eu finibus mi fermentum vel. Pellentesque iaculis elementum ligula, in aliquam turpis. Phasellus congue, magna non ultrices consequat, magna lacus finibus mauris, vitae vulputate velit quam quis ipsum. Morbi ut ipsum porttitor, hendrerit erat a, fringilla dolor. Vivamus ultrices nibh eu lorem sagittis hendrerit. Quisque porttitor volutpat dolor vel tincidunt. Aliquam ut imperdiet sapien. Morbi eget facilisis velit. Mauris malesuada vitae erat eget ultricies. Quisque tincidunt sapien quis nulla tincidunt ultrices. Sed ultrices consectetur diam vitae semper. Mauris finibus dignissim condimentum. Praesent congue at neque et condimentum.\n" +
                "\n",new Long(21),0);


        vinir.setMembers(new ArrayList<User>(Arrays.asList(birgir, adam,marino,isak)));
        Group aular = new Group("Aularnir","fokking aular",new Long(22),1);
        aular.setMembers(new ArrayList<User>(Arrays.asList(marino, adam,isak)));

        Group eldri = new Group("Eldriborgarar","Ellismellirnir adam og birgir",new Long(23),0);
        eldri.setMembers(new ArrayList<User>(Arrays.asList(birgir, adam,isak)));

        Comment skitaComment = new Comment(new Long(31),new Long(42),"ertu að grínast eða",new Long(13));
        Comment helladComment = new Comment(new Long(32),new Long(41),"vá hvað ég er spenntur",new Long(11));

        Event afmaeli = new Event(new Long(41),"AAAAMLI","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum et ultricies dui. Cras commodo sem sed justo bibendum, nec placerat dui commodo. Nunc id consectetur odio. Maecenas ut tristique lacus. Aliquam semper blandit felis non ultricies. Suspendisse sit amet felis nec justo ornare vehicula eget in tellus. Quisque convallis sed ex ut ultrices. Duis luctus porttitor augue, at venenatis nulla dictum id. Quisque eu molestie nulla, sit amet tincidunt turpis. Curabitur volutpat, lacus dapibus imperdiet mollis, leo erat auctor metus, a molestie justo nunc ut eros. Integer nec tristique massa. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Quisque pretium diam ac tellus pellentesque, non mattis arcu tempor.\n" +
                "\n" +
                "Suspendisse vel sapien efficitur, pellentesque eros ut, dignissim nisl. Pellentesque ullamcorper a mauris non dictum. Donec a magna nec lorem tincidunt aliquam. Ut id tincidunt turpis, nec egestas sem. In vulputate dui gravida nisi dignissim sodales. Cras congue leo eget consequat fringilla. Morbi viverra augue in nisi tristique molestie nec at dui. Cras eu odio et ligula posuere ultricies non nec lacus. Duis luctus neque id ipsum facilisis fringilla. Ut at nisi vel lorem tincidunt mattis ac et massa. Ut semper faucibus nisl ut accumsan. Mauris nec tempus justo. Sed eu nibh diam. Integer sed semper est, id varius tortor. Donec et dignissim diam, sit amet imperdiet libero. Etiam eget felis hendrerit, consequat nunc eget, dapibus lectus.\n" +
                "\n" +
                "Quisque non fermentum justo. Phasellus pulvinar ante sed leo viverra blandit. Maecenas vel pharetra nibh. Nullam nec massa tincidunt, tempor dui id, bibendum lorem. In ac facilisis nunc. Nullam quis rutrum risus. In hac habitasse platea dictumst. Praesent dictum erat id lectus commodo tincidunt. Aenean quam ante, mattis a erat vitae, viverra fringilla sapien. Integer id venenatis tellus. Phasellus massa felis, tincidunt quis iaculis quis, sagittis id nulla. Fusce non blandit ex, ut porttitor justo. Morbi sit amet ultricies orci. Donec metus odio, bibendum sed rutrum vitae, aliquet a sapien. Aenean urna est, placerat nec porta et, molestie eu enim.\n" +
                "\n" +
                "Etiam egestas sagittis ipsum. Nam facilisis gravida ante quis commodo. Quisque in urna vel sapien sodales iaculis. In non felis metus. Fusce vel blandit odio. Quisque pulvinar eros est, non ultricies mauris fermentum id. Nam id fermentum arcu. Nunc tortor felis, semper et luctus sed, ornare fringilla sem. Nulla facilisi. Mauris eget quam quis nunc ornare luctus. Fusce vitae blandit est, ac tincidunt orci. Integer diam massa, ullamcorper vitae tortor non, feugiat condimentum sapien. Morbi finibus lorem nec ligula iaculis molestie. In hac habitasse platea dictumst. Curabitur malesuada, diam sed pulvinar blandit, justo urna varius odio, pharetra sollicitudin nisi risus vel eros.\n" +
                "\n" +
                "Aliquam ex neque, luctus sodales eros eget, vestibulum tristique risus. Pellentesque auctor quam magna, eu finibus mi fermentum vel. Pellentesque iaculis elementum ligula, in aliquam turpis. Phasellus congue, magna non ultrices consequat, magna lacus finibus mauris, vitae vulputate velit quam quis ipsum. Morbi ut ipsum porttitor, hendrerit erat a, fringilla dolor. Vivamus ultrices nibh eu lorem sagittis hendrerit. Quisque porttitor volutpat dolor vel tincidunt. Aliquam ut imperdiet sapien. Morbi eget facilisis velit. Mauris malesuada vitae erat eget ultricies. Quisque tincidunt sapien quis nulla tincidunt ultrices. Sed ultrices consectetur diam vitae semper. Mauris finibus dignissim condimentum. Praesent congue at neque et condimentum.\n" +
                "\n",21,"party","22.05","23.05","Grafarvoginum hjá marino",3,0);

        afmaeli.setGoing(new ArrayList<User>(Arrays.asList(birgir, adam, marino)));
        afmaeli.setComments(new ArrayList<Comment>(Arrays.asList(helladComment)));

        Event aulahittingur = new Event(new Long(42),"Aulahittingur","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum et ultricies dui. Cras commodo sem sed justo bibendum, nec placerat dui commodo. Nunc id consectetur odio. Maecenas ut tristique lacus. Aliquam semper blandit felis non ultricies. Suspendisse sit amet felis nec justo ornare vehicula eget in tellus. Quisque convallis sed ex ut ultrices. Duis luctus porttitor augue, at venenatis nulla dictum id. Quisque eu molestie nulla, sit amet tincidunt turpis. Curabitur volutpat, lacus dapibus imperdiet mollis, leo erat auctor metus, a molestie justo nunc ut eros. Integer nec tristique massa. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Quisque pretium diam ac tellus pellentesque, non mattis arcu tempor.\n" +
                "\n" +
                "Suspendisse vel sapien efficitur, pellentesque eros ut, dignissim nisl. Pellentesque ullamcorper a mauris non dictum. Donec a magna nec lorem tincidunt aliquam. Ut id tincidunt turpis, nec egestas sem. In vulputate dui gravida nisi dignissim sodales. Cras congue leo eget consequat fringilla. Morbi viverra augue in nisi tristique molestie nec at dui. Cras eu odio et ligula posuere ultricies non nec lacus. Duis luctus neque id ipsum facilisis fringilla. Ut at nisi vel lorem tincidunt mattis ac et massa. Ut semper faucibus nisl ut accumsan. Mauris nec tempus justo. Sed eu nibh diam. Integer sed semper est, id varius tortor. Donec et dignissim diam, sit amet imperdiet libero. Etiam eget felis hendrerit, consequat nunc eget, dapibus lectus.\n" +
                "\n" +
                "Quisque non fermentum justo. Phasellus pulvinar ante sed leo viverra blandit. Maecenas vel pharetra nibh. Nullam nec massa tincidunt, tempor dui id, bibendum lorem. In ac facilisis nunc. Nullam quis rutrum risus. In hac habitasse platea dictumst. Praesent dictum erat id lectus commodo tincidunt. Aenean quam ante, mattis a erat vitae, viverra fringilla sapien. Integer id venenatis tellus. Phasellus massa felis, tincidunt quis iaculis quis, sagittis id nulla. Fusce non blandit ex, ut porttitor justo. Morbi sit amet ultricies orci. Donec metus odio, bibendum sed rutrum vitae, aliquet a sapien. Aenean urna est, placerat nec porta et, molestie eu enim.\n" +
                "\n" +
                "Etiam egestas sagittis ipsum. Nam facilisis gravida ante quis commodo. Quisque in urna vel sapien sodales iaculis. In non felis metus. Fusce vel blandit odio. Quisque pulvinar eros est, non ultricies mauris fermentum id. Nam id fermentum arcu. Nunc tortor felis, semper et luctus sed, ornare fringilla sem. Nulla facilisi. Mauris eget quam quis nunc ornare luctus. Fusce vitae blandit est, ac tincidunt orci. Integer diam massa, ullamcorper vitae tortor non, feugiat condimentum sapien. Morbi finibus lorem nec ligula iaculis molestie. In hac habitasse platea dictumst. Curabitur malesuada, diam sed pulvinar blandit, justo urna varius odio, pharetra sollicitudin nisi risus vel eros.\n" +
                "\n" +
                "Aliquam ex neque, luctus sodales eros eget, vestibulum tristique risus. Pellentesque auctor quam magna, eu finibus mi fermentum vel. Pellentesque iaculis elementum ligula, in aliquam turpis. Phasellus congue, magna non ultrices consequat, magna lacus finibus mauris, vitae vulputate velit quam quis ipsum. Morbi ut ipsum porttitor, hendrerit erat a, fringilla dolor. Vivamus ultrices nibh eu lorem sagittis hendrerit. Quisque porttitor volutpat dolor vel tincidunt. Aliquam ut imperdiet sapien. Morbi eget facilisis velit. Mauris malesuada vitae erat eget ultricies. Quisque tincidunt sapien quis nulla tincidunt ultrices. Sed ultrices consectetur diam vitae semper. Mauris finibus dignissim condimentum. Praesent congue at neque et condimentum.",
                22,"party","05.05","07.05","B5",2,1);

        aulahittingur.setGoing(new ArrayList<User>(Arrays.asList(adam)));
        aulahittingur.setComments(new ArrayList<Comment>(Arrays.asList(skitaComment)));

        Event fobo = new Event(new Long(43),"Fóbó","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum et ultricies dui. Cras commodo sem sed justo bibendum, nec placerat dui commodo. Nunc id consectetur odio. Maecenas ut tristique lacus. Aliquam semper blandit felis non ultricies. Suspendisse sit amet felis nec justo ornare vehicula eget in tellus. Quisque convallis sed ex ut ultrices. Duis luctus porttitor augue, at venenatis nulla dictum id. Quisque eu molestie nulla, sit amet tincidunt turpis. Curabitur volutpat, lacus dapibus imperdiet mollis, leo erat auctor metus, a molestie justo nunc ut eros. Integer nec tristique massa. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Quisque pretium diam ac tellus pellentesque, non mattis arcu tempor.\n" +
                "\n" +
                "Suspendisse vel sapien efficitur, pellentesque eros ut, dignissim nisl. Pellentesque ullamcorper a mauris non dictum. Donec a magna nec lorem tincidunt aliquam. Ut id tincidunt turpis, nec egestas sem. In vulputate dui gravida nisi dignissim sodales. Cras congue leo eget consequat fringilla. Morbi viverra augue in nisi tristique molestie nec at dui. Cras eu odio et ligula posuere ultricies non nec lacus. Duis luctus neque id ipsum facilisis fringilla. Ut at nisi vel lorem tincidunt mattis ac et massa. Ut semper faucibus nisl ut accumsan. Mauris nec tempus justo. Sed eu nibh diam. Integer sed semper est, id varius tortor. Donec et dignissim diam, sit amet imperdiet libero. Etiam eget felis hendrerit, consequat nunc eget, dapibus lectus.\n" +
                "\n" +
                "Quisque non fermentum justo. Phasellus pulvinar ante sed leo viverra blandit. Maecenas vel pharetra nibh. Nullam nec massa tincidunt, tempor dui id, bibendum lorem. In ac facilisis nunc. Nullam quis rutrum risus. In hac habitasse platea dictumst. Praesent dictum erat id lectus commodo tincidunt. Aenean quam ante, mattis a erat vitae, viverra fringilla sapien. Integer id venenatis tellus. Phasellus massa felis, tincidunt quis iaculis quis, sagittis id nulla. Fusce non blandit ex, ut porttitor justo. Morbi sit amet ultricies orci. Donec metus odio, bibendum sed rutrum vitae, aliquet a sapien. Aenean urna est, placerat nec porta et, molestie eu enim.\n" +
                "\n" +
                "Etiam egestas sagittis ipsum. Nam facilisis gravida ante quis commodo. Quisque in urna vel sapien sodales iaculis. In non felis metus. Fusce vel blandit odio. Quisque pulvinar eros est, non ultricies mauris fermentum id. Nam id fermentum arcu. Nunc tortor felis, semper et luctus sed, ornare fringilla sem. Nulla facilisi. Mauris eget quam quis nunc ornare luctus. Fusce vitae blandit est, ac tincidunt orci. Integer diam massa, ullamcorper vitae tortor non, feugiat condimentum sapien. Morbi finibus lorem nec ligula iaculis molestie. In hac habitasse platea dictumst. Curabitur malesuada, diam sed pulvinar blandit, justo urna varius odio, pharetra sollicitudin nisi risus vel eros.\n" +
                "\n" +
                "Aliquam ex neque, luctus sodales eros eget, vestibulum tristique risus. Pellentesque auctor quam magna, eu finibus mi fermentum vel. Pellentesque iaculis elementum ligula, in aliquam turpis. Phasellus congue, magna non ultrices consequat, magna lacus finibus mauris, vitae vulputate velit quam quis ipsum. Morbi ut ipsum porttitor, hendrerit erat a, fringilla dolor. Vivamus ultrices nibh eu lorem sagittis hendrerit. Quisque porttitor volutpat dolor vel tincidunt. Aliquam ut imperdiet sapien. Morbi eget facilisis velit. Mauris malesuada vitae erat eget ultricies. Quisque tincidunt sapien quis nulla tincidunt ultrices. Sed ultrices consectetur diam vitae semper. Mauris finibus dignissim condimentum. Praesent congue at neque et condimentum."
                ,23,"sports","25.03","25.03","Ásgarði",10,1);

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
        return new Event(new Long(-1),"Ekkert fannst","Engar upplýsingar",-1,"ekkert","25.03","25.03","Ekkert",10,0);
    }

    public Group getGroupById(Long id){
        for(Group g : getGroups()){
            if(g.getId().equals(id)){
                return g;
            }
        }
        return new Group("ekkert fannst","Engar upplýsingar",new Long(-1),0);
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
