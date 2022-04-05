public class Classmates {
    private final Integer id;
    private final String name;
    private final Integer score;

    public Classmates(Integer id, String name, Integer score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public void info()  {
        System.out.printf("Идентификатор: %s, Имя: %s, Баллы: %s", id, name, score);
    }
}
