package entities;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class Item {
    private int id;

    @NotEmpty
    private String label;

    @NotNull
    private LocalDate date;

    public Item() {

    }

    public Item(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public Item(String label, LocalDate date) {
        this.label = label;
        this.date = date;
    }

    public Item(int id, String label, LocalDate date) {
        this(label, date);
        this.id = id;
    }
}
