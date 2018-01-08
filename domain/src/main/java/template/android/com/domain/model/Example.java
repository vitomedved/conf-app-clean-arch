package template.android.com.domain.model;

public final class Example {

    public static final Example EMPTY = new Example(0);

    public final int id;

    public Example(final int id) {
        this.id = id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Example example = (Example) o;

        return id == example.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Example{" +
                "id=" + id +
                '}';
    }
}
