package main.Evenement;

import java.util.ArrayList;
import java.util.List;

public class Participants {

    private final List<Participant> participants;

    public Participants(){
        this.participants = new ArrayList<>();
    }

    public Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public void addParticipant(Participant participant){
        participants.add(participant);
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public String toString(){
        String str = "[";
        for(Participant participant : participants){
            str += participant.getParticipant() + ", ";
        }
        return str.substring(0, str.length() - 2) + "]";
    }
}
