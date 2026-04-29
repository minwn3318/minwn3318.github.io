---
title: graph-analy---그래프와-분석
date: 2026-04-27 10:00:00 +0900
categories: [data, framework]
tags: [data, pandas]
---

# graph & Analy - 그래프와 분석
---
## graph
### matplotlib
1. 설치
```
# 기본
import matplotlib


# 그래프 그리기 전용
import matplotlib.pyplot as plt
```


2. 선 그래프
- 겹쳐서 그릴 수 있음
```
plt.plot(x,y,[color][marker][linestyle])
```


3. 라벨 지정
```
# x 라벨
plt.xlable()


# y 라벨
plt.ylabel()
```


4. 제목 지정
```
plt.title()
```


5. 범례, 괘선 추가
```
# 범례
plt.legend()


# 괘선
plt.grid()
```


6. 화면 출력
```
plt.show()
```



### seaborn
1. 설치
```
import seaborn as sns
```


2. 히스토그램
```
sns.hist(x, data)
```



3. density 그래프
```
# x, 데이터 프레임, x 구분 기준이 되는 범주형 데이터(hue) 
sns.kdeplot(x, data, hue)
```


4. 박스 그래프
``` 
# x, y, 데이터 프레임, x 구분 기준이 되는 범주형 데이터(hue) 
sns.kdeplot(x, y, data, hue)
```


5. 분포 그래프
``` 
sns.displot(x)
```


6. 산점도
```
sns.scatter(x,y,data)
```


7. 산점도와 히스토그램 조인
``` 
# x, y, 데이터 프레임, x 구분 기준이 되는 범주형 데이터(hue) 
sns.jointplot(x, y, data, hue)
```


8. 변수 간의 산점도와 분포 한번에 시각화
``` 
# 데이터 프레임, 구분 기준이 되는 범주형 데이터(hue) 
sns.pairplot(data, hue)
```


9. 빈도 표시 막대 그래프
``` 
# x, 데이터 프레임
sns.countplot(x, data)
```


10. 범주 표시 막대 그래프
``` 
# x, y, 데이터 프레임
sns.countplot(x, data)
```


11. 히트맵
```
# 범주집계 정도 히트맵
a= df.groupby(value)
b = a.pivot(index, columns, values)
sns.heatmap(b)


# 산점도 히트맵
a= df.corr 
sns.heatmap(a)
```


---
## analy
### pandas
1. 상관관계
```
df.corr()
```

2. 교차표


### spicy
1. 설치
```
import spicy.stats as spst 
```


2. 상관계수와 p-value
```
spst.pearsonr(x, y)
```


3. t-통계량과 분산분석
```
# t 통계량
spst.ttest_ind(x, y)


# 분산분석
spst.t_oneway(a,b,c)
```


4. 카이제곱검정
```
spst.chi2_contingency(table)
```
