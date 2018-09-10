# Package Installs ====
install.packages('geosphere')
install.packages('snow')
install.packages('doParallel')

# Library Setup ====
library(data.table)
library(geosphere)
library(parallel)
library(doParallel)

# Utility Setup ====
setwd("~/R/google-cloud-nyctaxi")
cores <- detectCores()
cluster <- makePSOCKcluster(cores)
print(paste(c('Total Avaliable Cores: ', cores), collapse = " "))

# Function Setup ====
CombineDataFrames <- function(dt1, dt2) {
  dt2$fare_amount = rep(0,9914)
  dt3 = rbindlist(list(dt1,dt2), use.names = TRUE)
  remove(dt1, dt2)
  return(dt3)
}

ComputeLinearDistanceBetweenPoints <- function(rowVal) {
  lo1 = rowVal[3]
  la1 = rowVal[4]
  lo2 = rowVal[5]
  la2 = rowVal[6]
  c1 = c(lo1, la1)
  c2 = c(lo2, la2)
  val <- geosphere::distGeo(c1, c2)
  remove(lo1, la1, lo2, la2 ,c1, c2, rowVal)
  return(val)
}

ComputeTimeSinceDayStartSec <- function(time.day) {
  time.array <- unlist(strsplit(time.day, ":"))
  val <- (as.numeric(time.array[1])*24*60) + (as.numeric(time.array[2])*60) + (as.numeric(time.array[3]))
  remove(time.array)
  return(val)
}

ConvertDateToOneNumeric <- function(date.day) {
  date.array <- unlist(strsplit(date.day, "-"))
  val <- as.numeric(paste(date.array[1],date.array[2],date.array[3], sep=''))
  remove(date.array)
  return(val)
}

RemoveBadLongLatData <- function(dt.temp) {
  long_min = -75
  long_max = -73
  lat_min = 40
  lat_max = 41.5
  dt.temp <- dt.temp[dt.temp$dropoff_latitude <= lat_max]
  dt.temp <- dt.temp[dt.temp$dropoff_longitude <= long_max]
  dt.temp <- dt.temp[dt.temp$pickup_latitude <= lat_max]
  dt.temp <- dt.temp[dt.temp$pickup_longitude <= long_max]
  
  dt.temp <- dt.temp[dt.temp$dropoff_latitude >= lat_min]
  dt.temp <- dt.temp[dt.temp$dropoff_longitude >= long_min]
  dt.temp <- dt.temp[dt.temp$pickup_latitude >= lat_min]
  dt.temp <- dt.temp[dt.temp$pickup_longitude >= long_min]
  return(dt.temp)
}

CreateTimeCOl <- function(dt.temp) {
  data <- substr(dt.temp$key, 12, 19)
  col <- c()
  for(time in data) {
    val <- ComputeTimeSinceDayStartSec(time)
    col <- c(col, val)
  }
  remove(data, dt.temp, val, time)
  return(col)
  stopCluster(cluster)
}

CreateDateCol <- function(dt.temp) {
  data <- substr(dt.temp$key, 0, 10)
  col <- c()
  for(date in data) {
    val <- ConvertDateToOneNumeric(date)
    col <- c(col, val)
  }
  remove(data, dt.temp, val, date)
  return(col)

}

# Loading Data ====
# train: 1:55423856
# test: 55423857:55433770 
dt <- CombineDataFrames(fread('train.csv', header = T, sep = ',', nThread = 10, nrows = 39656), 
                        fread('test.csv', header = T, sep = ',', nThread = 10))

# Feature Engineering ==== 

# Add time column
dt$time.day <- CreateTimeCOl(dt)

# Add date column
dt$time.date <- CreateDateCol(dt)

# Remove NA Values : TEST HAS NO NA VALUES
dt <- subset(dt, select = -c(key))
dt[dt$fare_amount < 2.5] = NA
dt[dt$passenger_count <= 0] = NA
dt <- na.omit(dt, cols = c('dropoff_longitude', 'dropoff_latitude', 'fare_amount', 'passenger_count'))
dt <- RemoveBadLongLatData(dt)

# Add col corresponding to linear geographic distance between long / lat points
dt$geo_dist <- parRapply(cluster, dt, ComputeLinearDistanceBetweenPoints)

dt[dt$geo_dist == 0] = NA
dt <- na.omit(dt, cols = c('geo_dist'))

  

# Model Selection ====

# Random Sampling
train.index <- sample(1:nrow(dt), 0.70 * nrow(dt))
test.index <- setdiff(1:nrow(dt), train.index)

set.seed(101)
dt.train <- dt[train.index, c(-2,-3,-4,-5,-6)]
dt.test <- dt[test.index, c(-2,-3,-4,-5,-6)]

lm.fit <- lm(fare_amount ~ time.date + geo_dist, data = dt.train)
summary(lm.fit)

lm.probs = predict(lm.fit, newdata = dt.test)
lm.probs <- round(lm.probs, 2)

sqrt(mean((dt.test$fare_amount- lm.probs)^2))

