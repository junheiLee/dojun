package src.main.bowling.io;

public class Output {

    private String boardForm = "";

    public Output generateBoardForm(int participants) {

        this.boardForm = BoardForm.generate(participants);
        return this;
    }

    public void show() {
        System.out.println(boardForm);
    }
}
