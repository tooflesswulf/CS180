package xu.albert.project_5;
import android.os.Parcel;
import android.os.Parcelable;

class Counter implements Parcelable {
    private String name;
    private int val;

    public Counter(String name) {
        this(name, 0);
    }

    public Counter(String name, int val) {
        this.name = name;
        this.val = val;
    }

    public synchronized void increment() {
        val++;
    }

    public synchronized int getVal() { return val; }
    public synchronized void setVal(int val) { this.val = val; }

    public String getName() { return name; }

    @Override
    public String toString() {
        return name + ": " + val;
    }

    protected Counter(Parcel in) {
        name = in.readString();
        val = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(val);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Counter> CREATOR = new Parcelable.Creator<Counter>() {
        @Override
        public Counter createFromParcel(Parcel in) {
            return new Counter(in);
        }

        @Override
        public Counter[] newArray(int size) {
            return new Counter[size];
        }
    };
}
