install.packages("naniar")
install.packages("data.table")
install.packages("mice")
install.packages("glmnet")
install.packages("Metrics")
install.packages("fastDummies")

library(naniar)
library(data.table)
library(glmnet)
library(readr)
library(Metrics)
library(tidyr)
library(fastDummies)

# Utility Setup ====
setwd("~/University-Assignments/R/Melbourne")

Mode <- function(x) {
  ux <- na.omit(unique(x) )
  tab <- tabulate(match(x, ux)); ux[tab == max(tab) ]
}

invlog.trans <- function(x) {
  (10^(x))-1
}

# Loading Data ====
dt.train <- read_csv('train.csv')
dt.train <- data.frame(dt.train)

dt.test <- read_csv('test.csv')
dt.test$SalePrice = rep(0,length(dt.test$Id))
dt.test <- data.frame(dt.test)

#dt = rbindlist(list(dt.train, dt.test), use.names = TRUE)
dt = dt.train
#dt = dt.test
dt <- data.frame(dt)

# Feature Engineering
dt[,c("Id", "Alley", "PoolQC", "Fence", "MiscFeature", "FireplaceQu", "BsmtUnfSF", "MisFeature", "MiscVal")] <- NULL

dt$MasVnrType <- tidyr::replace_na(dt$MasVnrType, "NM")

dt$BsmtQual <- tidyr::replace_na(dt$BsmtQual, "NB")
dt$BsmtCond <- tidyr::replace_na(dt$BsmtCond, "NB")
dt$BsmtExposure <- tidyr::replace_na(dt$BsmtExposure, "NB")
dt$BsmtFinType1 <- tidyr::replace_na(dt$BsmtFinType1, "NB")
dt$BsmtFinType2 <- tidyr::replace_na(dt$BsmtFinType2, "NB")

dt[dt$BsmtQual == "NB", "BsmtFinSF1"] <- 0
dt[dt$BsmtQual == "NB", "BsmtFinSF2"] <- 0
dt[dt$BsmtQual == "NB", "BsmtHalfBath"] <- 0
dt[dt$BsmtQual == "NB", "BsmtFullBath"] <- 0

# Need to NOT HARDCODE these index values...
dt[949, "BsmtExposure"] <- Mode(dt$BsmtExposure)
dt[333, "BsmtFinType2"] <- Mode(dt$BsmtFinType2)

for(title in colnames(dt[, names(dt) != 'SalePrice'])) {
  if(sum(is.na(dt[, title])) > 0) {
    if(is.numeric(dt[, title])) {
      dt[is.na(dt[,title]), title] <- mean(as.numeric(dt[,title]), na.rm=TRUE)
    } else {
      dt[is.na(dt[,title]), title] <- Mode(dt[,title])
    }
  }
}
remove(title)

dt[, "TotalSF"] = dt[, "TotalBsmtSF"] + dt[, "X1stFlrSF"] + dt[, "X2ndFlrSF"]

dt[dt$MasVnrArea == 0, "MasVnrType"] <- "NM"
dt[dt$MasVnrType == "NM", "MasVnrArea"] <- 0

# Note, can do more engineering here:
#   Such as comparing to other dates inside the dataset instead
#   of just imputing a mean value.
dt[dt$GarageYrBlt < 1950 | dt$GarageYrBlt > 2010, "GarageYrBlt"] <- mean(as.numeric(dt[dt$GarageYrBlt >= 1950 & dt$GarageYrBlt <= 2010, "GarageYrBlt"]), na.rm=TRUE)
dt[dt$YrSold < 1950 | dt$YrSold > 2010, "YrSold"] <- mean(as.numeric(dt[dt$YrSold >= 1950 & dt$YrSold <= 2010, "YrSold"]), na.rm=TRUE)
dt[dt$YearBuilt < 1950 | dt$YearBuilt > 2010, "YearBuilt"] <- mean(as.numeric(dt[dt$YearBuilt >= 1950 & dt$YearBuilt <= 2010, "YearBuilt"]), na.rm=TRUE)
dt[dt$YearRemodAdd < 1950 | dt$YearRemodAdd > 2010, "YearRemodAdd"] <- mean(as.numeric(dt[dt$YearRemodAdd >= 1950 & dt$YearRemodAdd <= 2010, "YearRemodAdd"]), na.rm=TRUE)
dt[dt$MoSold < 0 | dt$MoSold > 12, "MoSold"] <- mean(as.numeric(dt[dt$MoSold >= 0 & dt$MoSold <= 12, "MoSold"]), na.rm=TRUE)

col_numeric = c()
col_cat = c()

for(name in colnames(dt)) {
  if(is.numeric(dt[1, name])) {
    col_numeric <- c(col_numeric, name)
  } else {
    col_cat <- c(col_cat, name)
  }
}
dt[,col_numeric] <- lapply(dt[,col_numeric], function(x) {log10(1+x)})

dt <- dummy_cols(dt)
for(name in colnames(dt)) {
  if(!is.numeric(dt[1, name])) {
    dt[,name] <- NULL
  }
}

# Feature Selection ====
x = model.matrix(SalePrice~., dt)[,-1]
y = dt$SalePrice

set.seed(1)
#train = array(1:(length(dt.train$Id) + length(dt.test$Id)))
#train[1:length(dt.train$Id)] = TRUE
train = sample(1:nrow(x), nrow(x)/2)
test = (-train)
y.test = y[test]


# Cross validaiton Lasso Lambda
cv.out = cv.glmnet(x[train,], y[train], alpha=1)
plot(cv.out)
bestlam= cv.out$lambda.min


# Lasso Model Prediction  / Testing
lasso.mod = glmnet(x[train,], y[train], alpha=1)
lasso.pred = predict(lasso.mod, s=bestlam, newx=x[test,])
rmsle(y.test, invlog.trans(lasso.pred))

#lasso.pred <- invlog.trans(lasso.pred)
#invlog.trans(y[1])
#lasso.pred[1]

# File Output
dt.out <- as.data.frame(lasso.pred)
dt.out <- data.frame(Id = 1461:2919, Label = dt.out)
colnames(dt.out)[colnames(dt.out) == "X1"] <-
  "SalePrice"

write_csv(dt.out, "submission.csv")
