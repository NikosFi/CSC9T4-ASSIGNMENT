//
//
//
//private Function<String, Ref> objectToAdd = (line) -> {
//
//
//        TextAreaPanel output = new TextAreaPanel();
//
//        String[] cols = line.split(",", -1);
////        List<String> cols = Arrays.asList(cols1);
//
//        String t, p, doi, date, jn, c, l, bt, e;
//        String[] a;
//        int py, v, i;
//
//
//        int dayAdded = 0;
//        int monthAdded = 0;
//        int yearAdded = 0;
//
//        String[] dateSplit = cols[5].split("/");
//        try {
//        dayAdded = Integer.parseInt(dateSplit[0]);
//        monthAdded = Integer.parseInt(dateSplit[1]);
//        yearAdded = Integer.parseInt(dateSplit[2]);
//        String dateToValidate = (dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0]);
//
//        if (!validateDate(dateToValidate)) {
//        throw new IllegalArgumentException();
//        }
//        } catch (NumberFormatException exception) {
//        if (dateSplit[0].equals("")) {
//        dayAdded = 0;
//        monthAdded = 0;
//        yearAdded = 0;
//        } else {
//        output.setText("The CSV contains dates that include invalid characters");
//        }
//        } catch (IllegalArgumentException exception) {
//        output.setText("The CSV that you provided includes dates that are not valid \n" +
//        exception.getMessage());
//        }
//
//        py = Integer.parseInt(cols[2]);
//        if (notNull(cols[6], cols[7], cols[8])) {
//        RefJournal refJournal;
//        return refJournal = new RefJournal(
//        t = cols[0],
//        a = cols[1].split(";"),
//        py,
//        p = cols[3],
//        doi = cols[4],
//        dayAdded,
//        monthAdded,
//        yearAdded,
//        jn = cols[6],
//        v = Integer.parseInt(cols[7]),
//        i = Integer.parseInt(cols[8])
//        );
//
//        }
//
//        if (notNull(cols[9], cols[10])) {
//        Ref refConference;
//        return refConference = new RefConference(
//        t = cols[0],
//        a = cols[1].split(";"),
//        py,
//        p = cols[3],
//        doi = cols[4],
//        dayAdded,
//        monthAdded,
//        yearAdded,
//        cols[9],
//        cols[10]
//        );
//        }
//
//        if (notNull(cols[11], cols[12])) {
//        RefBookChapter refBookChapter;
//        return refBookChapter = new RefBookChapter(
//        t = cols[0],
//        a = cols[1].split(";"),
//        py,
//        p = cols[3],
//        doi = cols[4],
//        dayAdded,
//        monthAdded,
//        yearAdded,
//        cols[11],
//        cols[12]
//        );
//
//        } else {
//
//        return null;
//        }
//
//        };
