package game.restcontroller;

import game.datahandler.HighScoreTable;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Path("/highScore")
@RestController
public class HighScoreTableController {

    private HighScoreTable highScoreTable;

    @Autowired
    public HighScoreTableController(HighScoreTable highScoreTable) {
        super();
        this.highScoreTable = highScoreTable;
    }

    @GET
    @Path("/scores")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getThreeBestScores() {
        return highScoreTable.getThreeBestScores();
    }

}
