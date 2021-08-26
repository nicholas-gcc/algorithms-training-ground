import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Solution {
    public static String[] solution(String[] l) {
        ArrayList<Version> versions = new ArrayList<>();
        String[] versionsOutput = new String[l.length];
        for (int i = 0; i < l.length; i++) {
            versions.add(new Version(l[i]));
        }
        Collections.sort(versions, new SortByVersion());
        int versionsOutputIndex = 0;
        for (Version v : versions) {
            versionsOutput[versionsOutputIndex] = v.toString();
            versionsOutputIndex++;
        }
        return versionsOutput;
    }

    public static void main(String[] args) {
        String[] versions = {"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"};
        String[] sortedVersions = solution(versions);
        for (int i = 0; i < sortedVersions.length; i++) {
            System.out.println(sortedVersions[i]);
        }
    }

    private static class Version {
        String versionString;
        Integer major;
        Integer minor;
        Integer revision;

        public Version(String versionString) {
            this.versionString = versionString;
            String[] versionStringArray = versionString.split("\\.");
            if (versionStringArray.length == 1) {
                this.major = Integer.parseInt(versionStringArray[0]);
                this.minor = null;
                this.revision = null;
            }
            else if (versionStringArray.length == 2) {
                this.major = Integer.parseInt(versionStringArray[0]);
                this.minor = Integer.parseInt(versionStringArray[1]);
                this.revision = null;
            }
            else {
                this.major = Integer.parseInt(versionStringArray[0]);
                this.minor = Integer.parseInt(versionStringArray[1]);
                this.revision = Integer.parseInt(versionStringArray[2]);
            }
        }

        public Integer getMajor() {
            return this.major;
        }

        public Integer getMinor() {
            return this.minor;
        }

        public Integer getRevision() {
            return this.revision;
        }

        @Override
        public String toString() {
            return this.versionString;
        }
    }

    static class SortByVersion implements Comparator<Version> {
        @Override
        public int compare(Version t1, Version t2) {
            if (t1.getMajor().equals(t2.getMajor())) {
                return compareMinor(t1, t2);
            }
            else return t1.getMajor().compareTo(t2.getMajor());
        }

        public int compareMinor(Version t1, Version t2) {
            if (t1.getMinor() == null && t2.getMinor() != null) {
                return -1;
            }
            else if (t1.getMinor() != null && t2.getMinor() == null) {
                return 1;
            }
            else if (t1.getMinor() == null && t2.getMinor() == null) {
                return 0;
            }
            //both minors not null
            else {
                if (t1.getMinor().equals(t2.getMinor())) {
                    return compareRevision(t1, t2);
                }
                else return t1.getMinor().compareTo(t2.getMinor());
            }
        }

        public int compareRevision(Version t1, Version t2) {
            if (t1.getRevision() == null && t2.getRevision() != null) {
                return -1;
            }
            else if (t1.getRevision() != null && t2.getRevision() == null) {
                return 1;
            }
            else if (t1.getRevision() == null && t2.getRevision() == null) {
                return 0;
            }
            else {
                if (t1.getRevision().equals(t2.getRevision())) {
                    return 0;
                }
                else return t1.getRevision().compareTo(t2.getRevision());
            }
        }
    }
}