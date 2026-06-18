---
title: machinelearing--머신러닝
date: 2026-04-27 10:00:00 +0900
categories: [data, framework]
tags: [data, sklearn]
---

# machineLearing- 머신러닝
---
**핵심**
- sklearn이라는 모듈 활용


---
## 데이터 전처리
1. 가변수화
```
pd.get_dummies(data=df, columns)
```


2. 데이터 분리
```
from sklearn.model_selection import train_test_splict

x_trian, x_val, y_train, y_val = train_test_splict(x,y,train_size,random_state)
```


## 모델링 
1. 설치하기
```
# 회귀형
## 선형
from sklearn.linear_model import LinearRegression
## 클러스팅
from sklearn.neighbors import KNeighborRegression
## 트리
from sklearn.tree import DecisionTreeRegression


# 분류형
## 클러스팅
from sklearn.neighbors import KNeighborsClassifer
## 트리
from sklearn.tree import DecisionTreeClassifer
## 로지스틱
from sklearn.linear_model import LogisticRegrression


# 앙상블 (회귀, 분류 포함)
## 랜덤 포레스트
from sklearn.ensemble import RandomForest
## 부스팅
from sklearn.ensemble import GradientBoosting
## xgb 부스팅
from sklearn.ensemble import XGBoost
## 라이팅
from sklearn.ensemble import LightGBM

```


2. 선언하기
```
modle = 모듈명()
```


3. 학습하기
```
model.fit(x_train, y_train)
```


4. 예측하기
```
y_pred = model.predict(x_val)
```


5. 평가하기
```
# 회귀형
## 절대값과 오차
from sklearn.metrics import mean_absoulte_error
## 절대값과 오차 제곱
from sklearn.metrics import mean_squared_error
## 절대값과 오차 퍼센트
from sklearn.metrics import mean_absoulte_percentage_error
## r2 점수
from sklearn.metrics import r2_score

# 분류형
## f1 점수
from sklearn.metrics import f1_socore
## 리콜, 정확도 정도
from sklearn.metrics import confusion_matrix
## f1, 리콜, 정확도 종합 정도
from sklearn.metrics import classification_report


# 평가하는 코드
평가(y_val, y_pred)
```