package com.eltiempo.bl.utils.io;

import com.eltiempo.bl.data.ReportDTO;
import com.eltiempo.bl.utils.df.DateProcessor;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ScreenshotMaker {

    public void capture(ReportDTO reportDTO, File screen) throws IOException {
        var fp = new FileProcessor();
        fp.writeScreenShot(screen, reportDTO.getReportID(),
                new DateProcessor().parse2BasicDateTime(Timestamp.valueOf(LocalDateTime.now())));
    }
}
