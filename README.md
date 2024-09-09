1. Accuracy
   Accuracy measures the proportion of correct predictions out of the total predictions made by the model. It gives an overall idea of how often the model is correct.

Formula:
Accuracy = ( TP + TN ) / ( TP + TN + FP + FN )

Where:

- TP: True Positives (correctly predicted positive cases)
- TN: True Negatives (correctly predicted negative cases)
- FP: False Positives (incorrectly predicted positive cases)
- FN: False Negatives (incorrectly predicted negative cases)
  Example:
  In a binary classification task (e.g., spam vs. not spam):
- If there are 100 emails, and the model correctly predicts 90 emails (80 true negatives and 10 true positives), the accuracy would be: Accuracy = ( 80 + 10 ) / 100 = 0.9, (90% accuracy)
  When to Use:
- Accuracy is useful when the classes are balanced, meaning both positive and negative cases are equally important. For example, predicting cat vs. dog images where each class is equally represented.
  Limitation:
- In an imbalanced dataset, accuracy can be misleading. For example, if 95% of the emails are not spam, a model that predicts every email as "not spam" will have high accuracy (~95%) but fail to capture actual spam emails (0% recall).

2. Precision
   Precision focuses on the model's ability to correctly identify positive cases. It tells you what fraction of the instances that the model predicted as positive are actually positive.

Formula: Precision = TP / ( TP + FP )
Where:

- FP: False Positives (incorrectly labeled as positive)
  Example:
  If the model predicted 15 emails as spam, and only 12 of them are actually spam, the precision would be: Precision = 12 / ( 12 + 3 ) = 0.8, (80% precision)
  When to Use:
- Precision is important when the cost of false positives is high. For example:
  _ In spam detection, you don't want to label legitimate emails as spam.
  _ In fraud detection, you want to avoid flagging non-fraudulent transactions as fraud.
  Limitation:
- Precision alone doesn’t tell you about the false negatives (e.g., missed positive cases). You may have a high precision but low recall if the model is overly conservative in predicting positives.

3. Recall
   Recall (also known as sensitivity or true positive rate) measures the model's ability to correctly identify all positive cases. It tells you what fraction of the actual positive cases were predicted as positive.
   Formula: Recall= TP / ( TP + FN )
   Where:

- FN: False Negatives (missed positive cases)
  Example:
  If there are 20 actual spam emails, and the model correctly identified 15 of them, the recall would be: Recall = 15 / (15 + 5) = 0.75, (75% recall)
  When to Use:
- Recall is important when the cost of false negatives is high. For example:
  _ In medical diagnosis, you don’t want to miss any cases of a disease (i.e., avoid false negatives).
  _ In fraud detection, you want to catch as many fraudulent transactions as possible, even if it means flagging some legitimate ones.
  Limitation:
- A model with high recall may also have many false positives (low precision). It tells you how good the model is at capturing positives, but not how reliable those positive predictions are.

Key Differences: Accuracy vs Precision vs Recall
Metric Definition Formula Best Used When Limitation
Accuracy Measures the overall correctness of the model ( TP + TN ) / ( TP + TN + FP + FN ) Classes are balanced (equal number of positive and negative cases) Can be misleading in imbalanced datasets
Precision Focuses on the proportion of correctly predicted positives out of total predicted positives TP / ( TP + FP ) When false positives are costly (e.g., spam detection) Doesn’t consider false negatives (missed positive cases)
Recall Focuses on the proportion of correctly identified positives out of all actual positives TP / ( TP + FN ) When false negatives are costly (e.g., disease detection) Doesn’t consider false positives (incorrectly identified positives)
Example to Illustrate the Trade-offs:
Suppose you are building a fraud detection system:

- Accuracy: If 99% of transactions are legitimate, a model that always predicts "not fraud" will have 99% accuracy but will never detect fraud (0% recall).
- Precision: You may want a model that correctly identifies fraud cases with high precision to avoid flagging legitimate transactions as fraud (false positives), but this may lower recall.
- Recall: If missing fraudulent transactions (false negatives) is costly, you may prioritize recall, even if it means catching some legitimate transactions as potential fraud (false positives).

Trade-offs Between Precision and Recall:

- High Precision, Low Recall: The model is very strict in predicting positive cases. It predicts fewer positive cases, but when it does, it’s usually right (few false positives, but might miss many true positives).
  - Example: A fraud detection system that flags very few transactions but rarely gets it wrong.
- High Recall, Low Precision: The model is lenient in predicting positive cases. It predicts many positive cases but may include many false positives. \* Example: A cancer detection system that flags many potential patients to make sure it catches everyone, but some may be healthy.
  F1 Score: A Balance of Precision and Recall
- The F1 score is the harmonic mean of precision and recall. It is useful when you need a balance between the two, especially in cases of imbalanced data where either precision or recall alone may not provide the full picture.

Summary:

- Accuracy is good when you have balanced classes.
- Precision is important when false positives are costly.
- Recall is important when false negatives are costly.
- F1 Score balances precision and recall, especially useful for imbalanced datasets.
