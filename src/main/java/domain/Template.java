package domain;

public class Template {

    private final String example;

    public Template(String example) {
        this.example = example;
    }

    @Override
    public String toString() {
        return "Template{" +
                "example='" + example + '\'' +
                '}';
    }

    public String getExample() {
        return example;
    }
}
