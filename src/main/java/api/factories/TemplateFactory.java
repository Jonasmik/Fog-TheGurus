package api.factories;

public class TemplateFactory {

    private String example;

    public Boolean isValid() {
        if (this.example == null | this.example.equals("")) return false;
        return true;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getExample() {
        return example;
    }
}
