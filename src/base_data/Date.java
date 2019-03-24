package base_data;

public class Date {
    private int year;
    private int mouth;
    private int day;

    public Date(int year, int mouth, int day) {
        this.day = day;
        this.mouth = mouth;
        this.year = year;
        if (!isDateLegal()) {
            throw new IllegalArgumentException("Date Error");
        }
    }

    public Date(String date) throws IllegalArgumentException {
        if (!date.matches("^\\d{4}-\\d{1,2}-\\d{1,2}")) {
            throw new IllegalArgumentException("Date Should Be YYYY-MM-DD");
        }
        String[] parts = date.split("-");
        this.day = Integer.parseInt(parts[2]);
        this.mouth = Integer.parseInt(parts[1]);
        this.year = Integer.parseInt(parts[0]);
    }

    private boolean isDateLegal() {
        if (day < 0 || mouth < 0 || year < 0) {
            return false;
        }
        if (is30Day(mouth)) {
            return day <= 30;
        }
        if (is31Day(mouth)) {
            return day <= 31;
        }
        if (isLeap(year)) {
            return day <= 29;
        }
        else {
            return day <= 28;
        }
    }

    public Date addDay(int addDay) {
        Date newDate = new Date(year, mouth, day);
        int leftDay = addDay;
        while (leftDay > 0) {
            if (is31Day(newDate.mouth)) {
                if (newDate.day + leftDay > 31) {
                    leftDay -= 31 - newDate.day + 1;
                    newDate = nextMouth(newDate);
                }
                else {
                    newDate.day += leftDay;
                    leftDay = 0;
                }
            }
            if (is30Day(newDate.mouth)) {
                if (newDate.day + leftDay > 30) {
                    leftDay -= 30 - newDate.day + 1;
                    newDate = nextMouth(newDate);
                }
                else {
                    newDate.day += leftDay;
                    leftDay = 0;
                }
            }
            else {
                if (isLeap(newDate.year)) {
                    if (newDate.day + leftDay > 29) {
                        leftDay -= 29 - newDate.day + 1;
                        newDate = nextMouth(newDate);
                    }
                    else {
                        newDate.day += leftDay;
                        leftDay = 0;
                    }
                }
                else {
                    if (newDate.day + leftDay > 28) {
                        leftDay -= 28 - newDate.day + 1;
                        newDate = nextMouth(newDate);
                    }
                    else {
                        newDate.day += leftDay;
                        leftDay = 0;
                    }
                }
            }
        }
        return newDate;
    }

    private static Date nextMouth(Date date) {
        Date nextMouth = new Date(date.year, date.mouth + 1, 1);
        if (nextMouth.mouth > 12) {
            nextMouth.mouth = 1;
            nextMouth.year++;
        }
        return nextMouth;
    }

    private static boolean is31Day(int mouth) {
        return mouth == 1 || mouth == 3 || mouth == 5 ||
               mouth == 7 || mouth == 8 || mouth == 10 || mouth == 12;
    }

    private static boolean is30Day(int mouth) {
        return mouth == 4 || mouth == 6 || mouth == 9 || mouth == 11;
    }

    private static boolean isLeap(int year) {
        boolean normal = year % 4 == 0 || year % 100 != 0;
        boolean special = year % 400 == 0;
        return normal || special;
    }

    @Override
    public String toString() {
        return String.format("%d-%d-%d", year, mouth, day);
    }
}