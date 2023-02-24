import java.io.*;
import java.util.Scanner;
import hex.genmodel.easy.RowData;
import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.prediction.*;
import hex.genmodel.MojoModel;

public class main {
  public static void main(String[] args) throws Exception {

    EasyPredictModelWrapper.Config config = new EasyPredictModelWrapper.Config()
        .setModel(MojoModel.load("GBM_grid_1_AutoML_1_20230217_161120_model_266.zip"))
        .setEnableLeafAssignment(true)
        .setEnableContributions(true);
    EasyPredictModelWrapper model = new EasyPredictModelWrapper(config);

    //get our data to generate the prediction from a file
    Scanner sc = new Scanner(new File("insurance_predict.csv"));  
    sc.useDelimiter(",");   //sets the delimiter pattern

    RowData row = new RowData();
    row.put("age", sc.next().trim());
    row.put("sex", sc.next().trim());
    row.put("bmi", sc.next().trim());
    row.put("children", sc.next().trim());
    row.put("smoker", sc.next().trim());
    row.put("region", sc.next().trim());
    System.out.println(row);
    sc.close();

    RegressionModelPrediction p = model.predictRegression(row);

    System.out.println("Predicted cost: ");
    System.out.println(p.value);
    System.out.println("");

    System.out.println("Shapley contributions: ");
    for (int i=0; i < p.contributions.length; i++) {
      if (i > 0) {
        System.out.print(", ");
      }
      System.out.print(model.getContributionNames()[i] + ": " + p.contributions[i]);
    }
    System.out.println("");

  }
}