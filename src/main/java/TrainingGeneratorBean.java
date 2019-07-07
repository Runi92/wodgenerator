import entities.TrainingSchemaEntity;
import enums.BaseTrainingSchemaEnum;
import exceptions.*;
import generators.BaseTrainingGenerator;
import generators.WarmUpGenerator;
import generators.WodGenerator;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name="trainingGeneratorManagedBean")
@SessionScoped
@Getter
@Setter
public class TrainingGeneratorBean {
    //TODO Добавить коэффициент сложности (10%, 20% ... и т.д.)
    //TODO Добавить возможность загрузки файла схемы тренировки и его генерации
    private Date startPeriodDate;
    private Date trainingDate;

    private final static Logger logger = Logger.getLogger(TrainingGeneratorBean.class);
    private static final String FILE_NAME = "training.txt";

    public String generatePeriod(String schemaName) {
        String dailyTraining = "";
        try {
            TrainingSchemaEntity currentTrainingSchema = getTrainingSchema(schemaName);
            int trainingDayNumber = getTrainingDayNumber(startPeriodDate, trainingDate);
            logger.info("Создание генератора разминки");
            WarmUpGenerator warmUpGenerator = new WarmUpGenerator(trainingDayNumber, currentTrainingSchema);
            logger.info("Начало создания разминки");
            String warmUp = warmUpGenerator.generate();
            logger.info("Создание генератора дополнительных заданий");
            BaseTrainingGenerator baseTrainingGenerator = new BaseTrainingGenerator(trainingDayNumber, currentTrainingSchema);
            logger.info("Начало создания дополнительных заданий");
            String baseTraining = baseTrainingGenerator.generate();
            logger.info("Создание генератора WOD");
            WodGenerator wodGenerator = new WodGenerator();
            logger.info("Начало создания WOD");
            String wod = wodGenerator.generate();
            logger.info("Начало записи тренировки в файл");
            boolean isWriteTrainingToFile = writeTrainingToFile(warmUp, baseTraining, wod);
            if (isWriteTrainingToFile) {
                logger.info(String.join(" ", "Тренировка создана и записана в файл", FILE_NAME));
            } else {
                logger.error(String.join(" ", "Ошибка при создании и записи тренировки в файл", FILE_NAME));
            }
        } catch (UnsupportedBaseTrainingException e) {
            logger.error(e.getMessage());
            //TODO Добавить вывод ошибки на Facelet
        } catch (UnsupportedExerciseCountException e) {
            logger.error(e.getMessage());
            //TODO Добавить вывод ошибки на Facelet
        } catch (UnknowWodException e) {
            //TODO Добавить вывод ошибки на Facelet
            e.printStackTrace();
        } catch (UnsupportedSchemaException e) {
            //TODO Добавить вывод ошибки на Facelet
            e.printStackTrace();
        } catch (UnsupportedMeasurementUnitException e) {
            e.printStackTrace();
        }

        return dailyTraining;
    }

    /**
     * Метод получения сущности схемы тренировки из файла с описанием схемы
     * @param schemaFileName имя файла со схемой тренировок
     * @return сущность схемы тренировки
     */
    public TrainingSchemaEntity getTrainingSchema(String schemaFileName) {
        int weeksCount = 0;
        List<String> dailySchemas = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(String.join("\\", System.getProperty("user.dir"), "src\\main\\resources\\schemas", schemaFileName))))) {
            String weekLine;
            while ((weekLine = bufferedReader.readLine()) != null) {
                weeksCount++;
                String[] dailySchemasArray = weekLine.split("\\|");
                for (String dailySchema: dailySchemasArray) {
                    dailySchemas.add(dailySchema);
                }
            }
        } catch (FileNotFoundException e) {
            logger.error(String.join(" ", "Файл со схемой тренировок", schemaFileName, "отсутствует"), e);
        } catch (IOException e) {
            logger.error(String.join(" ", "Ошибка чтения файла со схемой тренировок", schemaFileName), e);
        }
        return TrainingSchemaEntity.builder().schemaName(schemaFileName).weeksCount(weeksCount).baseSchemas(dailySchemas).build();
    }

    public int getTrainingDayNumber(Date startPeriodDate, Date trainingDate) {
        int result = (int) ((trainingDate.getTime() - startPeriodDate.getTime()) / (1000 * 60 * 60 * 24));
        if (result > 21) {
            return getTrainingDayNumber(startPeriodDate, new Date((trainingDate.getTime() - 21 * 24 * 60 * 60 * 1000L)));
        } else {
            return result;
        }
    }

    private static boolean writeTrainingToFile(String warmUp, String extraWork, String wod) {
        boolean result = false;
        String training = String.join("\n", warmUp, extraWork, wod);
        try {
            FileUtils.writeStringToFile(new File(FILE_NAME), training, Charset.forName("UTF-8"));
            result = true;
        } catch (IOException e) {
            logger.error(String.join(" ", "Ошибка при записи тренировки", training, "в файл", FILE_NAME), e.getCause());
            result = false;
        }
        return result;
    }
}
