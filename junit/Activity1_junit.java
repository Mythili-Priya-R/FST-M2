package activities;

import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

public class Activity1_junit {

    List list;

    @BeforeAll
    public void setup()
    {
        list = new ArrayList();
        list.add("alpha");
        list.add("beta");
    }

}
