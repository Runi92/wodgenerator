import entities.TrainingSchemaEntity;
import exceptions.*;
import generators.BaseTrainingGenerator;
import generators.WarmUpGenerator;
import generators.WodGenerator;

import java.util.Date;

public class WarmUpBaseWodTrainingTest {
    public static void main(String[] args) {
        generateCycle();
//        generateForDay(1);
    }

    public static void generateForDay(int i) {
        try {
            Date currentDate = new Date();
            Date startPeriodDate = new Date(currentDate.getTime() + 1 * 24 * 60 * 60 * 1000L);
            Date trainingDate = new Date(startPeriodDate.getTime() + i * 24 * 60 * 60 * 1000L);
            TrainingGeneratorBean trainingGeneratorBean = new TrainingGeneratorBean();
            int trainingDay = trainingGeneratorBean.getTrainingDayNumber(startPeriodDate, trainingDate);
            String schemaName = "fiveDaySchema";
            TrainingSchemaEntity currentTrainingSchema = trainingGeneratorBean.getTrainingSchema(schemaName);
            WarmUpGenerator warmUpGenerator = new WarmUpGenerator(trainingDay, currentTrainingSchema);
            BaseTrainingGenerator baseTrainingGenerator = new BaseTrainingGenerator(trainingDay, currentTrainingSchema);
            WodGenerator wodGenerator = new WodGenerator();
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println(String.join(" ", "День", String.valueOf(trainingDay), currentTrainingSchema.getBaseSchemas().get(trainingDay - 1)));
            System.out.println();
            System.out.println("WARM UP");
            System.out.println(warmUpGenerator.generate());
            System.out.println("BASE TRAINING");
            System.out.println(baseTrainingGenerator.generate());
            System.out.println("WOD");
            System.out.println(wodGenerator.generate());
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------------");
        } catch (UnsupportedBaseTrainingException e) {
            e.printStackTrace();
        } catch (UnsupportedExerciseCountException e) {
            e.printStackTrace();
        } catch (UnknowWodException e) {
            e.printStackTrace();
        } catch (UnsupportedSchemaException e) {
            e.printStackTrace();
        } catch (UnsupportedMeasurementUnitException e) {
            e.printStackTrace();
        }
    }

    public static void generateCycle() {
        for (int i = 1; i < 22; i++) {
            generateForDay(i);
        }

    }
}
