package dl4j_sample;

import java.io.IOException;

import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.ui.api.UIServer;
import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction;

import org.deeplearning4j.ui.model.stats.StatsListener;
import org.deeplearning4j.ui.model.storage.InMemoryStatsStorage;
import org.deeplearning4j.core.storage.StatsStorage;

public class App {
  public String deepLearning4J() throws IOException {
    int batchSize = 64;
    DataSetIterator mnistTrain = new MnistDataSetIterator(batchSize, true, 12345);
    DataSetIterator mnistTest = new MnistDataSetIterator(batchSize, false, 12345);

    int inputSize = 28 * 28; // MNIST images are 28x28
    int hiddenLayerSize = 128; // Size of hidden layer
    int outputSize = 10; // 10 possible digits (0-9)

    MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
        .updater(new Adam(0.001)) // Learning rate and optimizer
        .list()
        .layer(0, new DenseLayer.Builder()
            .nIn(inputSize)
            .nOut(hiddenLayerSize)
            .activation(Activation.RELU)
            .build())
        .layer(1, new OutputLayer.Builder(LossFunction.NEGATIVELOGLIKELIHOOD)
            .nIn(hiddenLayerSize)
            .nOut(outputSize)
            .activation(Activation.SOFTMAX)
            .build())
        .build();

    MultiLayerNetwork model = new MultiLayerNetwork(conf);
    model.init();

    int numEpochs = 10; // Number of iterations over the dataset
    for (int epoch = 0; epoch < numEpochs; epoch++) {
      model.fit(mnistTrain); // Fit model on training data
      System.out.println("Epoch " + epoch + " complete");
    }

    Evaluation eval = model.evaluate(mnistTest);

    UIServer uiServer = UIServer.getInstance();
    StatsStorage statsStorage = new InMemoryStatsStorage();
    model.setListeners(new StatsListener(statsStorage));
    uiServer.attach(statsStorage);

    return eval.stats();
  }

  public static void main(String[] args) {
    try {
      System.out.println(new App().deepLearning4J());
    } catch (IOException e) {
    }
  }
}
