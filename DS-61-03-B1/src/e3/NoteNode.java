package e3;

import java.util.Objects;

import static e3.Melody.Accidentals.*;
import static e3.Melody.Notes;

class NoteNode{
    private final Melody.Notes note;
    private final Melody.Accidentals acc;
    private final float t;

    public NoteNode(Melody.Notes note, Melody.Accidentals acc, float t) {
        this.note = note;
        this.acc = acc;
        this.t = t;
    }

    public Melody.Notes getNote() {
        return note;
    }

    public Melody.Accidentals getAcc() {
        return acc;
    }

    public float getT() {
        return t;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteNode that = (NoteNode) o;

        if(Float.compare(that.t, t) == 0 && Objects.equals(note, that.note) && Objects.equals(acc, that.acc)) return true;

        /*Time is compared, if both notes have the same time, the exceptional cases of equivalence of notes and accidentals are compared */


        if (Float.compare(that.t, t) == 0){
           switch (note) {
               case RE, SOL, LA:
                  switch (acc){
                       case SHARP: if ((note.ordinal()+1 == that.note.ordinal()) && (that.acc == FLAT)) return true; break;
                        case FLAT: if ((note.ordinal()-1 == that.note.ordinal()) && (that.acc == SHARP)) return true; break;
                       default: break;
                    }
                  break;
               case MI,SI:
                   switch (acc){
                     case NATURAL, SHARP: if ((((note.ordinal()+1) % 7) == that.note.ordinal()) && (that.acc.ordinal() == ((acc.ordinal()+2)%3))) return true;
                      case FLAT:  if ((note.ordinal()-1 == that.note.ordinal()) && (that.acc == SHARP)) return true;
                  } break;
               case DO, FA:
                    switch (acc){
                        case NATURAL, FLAT: if ((((note.ordinal()+6) % 7) == that.note.ordinal()) && (that.acc.ordinal() == ((acc.ordinal()+1)%3))) return true;
                        case SHARP:  if ((note.ordinal()+1 == that.note.ordinal()) && (that.acc == FLAT)) return true;
                    } break;
            }
        }    
        return false;
    }


    @Override
    public int hashCode() {

        /*On the exceptional cases the hashcode is equaled to the hashcode of its equivalent note*/
        switch (note){
            case DO, RE, FA, SOL, LA:
                if (acc==SHARP) return Objects.hash(Notes.values()[note.ordinal()+1],FLAT,t);
                break;
            case MI, SI:
                if (acc==SHARP) return Objects.hash(Notes.values()[(note.ordinal()+1)%7],NATURAL,t);
                if (acc==NATURAL) return Objects.hash(Notes.values()[(note.ordinal()+1)%7],FLAT,t);
                break;
        }
        return Objects.hash(note, acc, t);

    }

}
