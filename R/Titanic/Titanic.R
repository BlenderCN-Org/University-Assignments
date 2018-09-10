# install.packages("naniar")
# install.packages("caTools")
# install.packages("dummies")
# install.packages("gbm")
# install.packages("gtools")


library(naniar)
library(dummies)
library(MASS)
library(dplyr)
library(readr)

setwd("~/Titanic")

# Complexy Impute Age based on title status
ImputateAge <- function(row, df.temp) {
  row <- as.data.frame(row)
  if (is.na(row$Age)) {
    row$Age = mean(df.temp$Age[df.temp$Title == row$Title], na.rm = TRUE)
  }
  return(row$Age)
}

# Dataset Cleaning
ResetAndCleanData <- function(df.train, df.test) {
  df.test$Survived = rep(0, 418)
  df.temp = rbind(df.train, df.test)
  
  df.temp$Title = gsub(".*, | .*", "", df.temp$Name)
  df.temp$Title[df.temp$Title == "Sir." |
                  df.temp$Title == "Don."] = "Mr."
  df.temp$Title[df.temp$Title == "Mlle." |
                  df.temp$Title == "Ms."] <- "Miss."
  df.temp$Title[df.temp$Title == "Mme." |
                  df.temp$Title == "Don."] <- "Mrs."
  df.temp$Title[df.temp$Title != "Mr." &
                  df.temp$Title != "Mrs." &
                  df.temp$Title != "Miss." &
                  df.temp$Title != "Master."] <- "Others"
  
  # df.temp$Age = by(df.temp, 1:nrow(df.temp), ImputateAge)
  for (row in 1:nrow(df.temp)) {
    df.temp[row, "Age"] = ImputateAge(df.temp[row,], df.temp)
  }
  
  df.temp$Embarked[is.na(df.temp$Embarked)] <-
    mode(df.temp$Embarked)
  
  df.temp$Fare[is.na(df.temp$Fare)] <-
    mean(df.temp$Fare, na.rm = TRUE)
  
  df.temp = df.temp[-c(1, 11)]
  
  return(df.temp)
}


# Data I/O ====
titanic_train <- read_csv("train.csv")
titanic_test <- read_csv("test.csv")


# Feature Engineering ====
df.train <- titanic_train # 1:891
df.test <- titanic_test # 892:1309
df <- ResetAndCleanData(df.train, df.test)
vis_miss(df)


# Testing Model Fit   ====
# Logistic Regression ----

# Creating Dummy Variables
df$Sex[df$Sex == 'female'] = 1
df$Sex[df$Sex == 'male'] = 0
df$Sex = as.numeric(as.character(df$Sex))

#df <- cbind(df, dummy(df$Title, sep = ":"))

# Random Sample Indicies of the Dataset
train.index <- sample(1:nrow(df.train), 0.70 * nrow(df.train))
test.index <- setdiff(1:nrow(df.train), train.index)

# Build Train/Test Variables
set.seed(101)
train <- df[train.index, c(-3, -7, -8, -9, -10, -11)]
test <- df[test.index, c(-3, -7, -8, -9, -10, -11)]

# Testing Errors
glm.fit = glm(Survived ~ Pclass + Sex + Age + SibSp,
              data = train,
              family = binomial)
summary(glm.fit)
glm.probs = predict(glm.fit, newdata = test, type = "response")

glm.pred = rep(0, 268)
glm.pred [glm.probs > .6] = 1

table <- table(glm.pred, test$Survived)
print(table)
print((table[1] + table[4]) / (table[1] + table[2] + table[3] + table[4]))









# Model Selection     ====
# Logistic Regression ----

# Creating Dummy Variables
df$Sex[df$Sex == 'female'] = 1
df$Sex[df$Sex == 'male'] = 0
df$Sex = as.numeric(as.character(df$Sex))

#df <- cbind(df, dummy(df$Title, sep = ":"))

# Build Train/Test Variables
train <- df[1:891, c(-3, -7, -8, -9, -10)]
test <- df[892:1309, c(-1, -3, -7, -8, -9, -10)]

set.seed(101)
glm.fit = glm.fit = glm(Survived ~ Pclass + Sex + Age + SibSp,
                        data = train,
                        family = binomial)
glm.probs = predict(glm.fit, newdata = test, type = "response")

glm.pred = rep(0, 418)
glm.pred [glm.probs > .6] = 1

y_pred <- as.data.frame(glm.pred)
predictions <- data.frame(PassengerId = 892:1309, Label = y_pred)
colnames(predictions)[colnames(predictions) == "glm.pred"] <-
  "Survived"
write_csv(predictions, "submission.csv")

