---
title: langchain---렌-체인
date: 2026-04-27 10:00:00 +0900
categories: [genai, theory]
tags: [llm, gpt, prompt]
---

# LangChain - 렌 체인
- 대규모 언어 모델을 활용하여 체인을 구축해, 복잡한 작업을 자동화하고 쉽게 수행할 수 있도록 돕는 개발론

- **구성요소**
1. models
2. prompt
3. index : vec db
4. memory
5. chain
6. agent & tool

## Models

---
## Prompt
- 인간이 인공지능에게 전달하는 지시문으로, 모델의 응답을 결정짓는 핵심 입력

    - LLM에게 무엇을 할지 설명하고, 질문, 명령, 조건등을 포함한다
    - LLM출력 품질을 좌우하는 가장 중요한 요소이다


---
## Prompt Enginnerning
- GPT에게 정확하고 유용한 응답을 얻기 위해 질문을 설계하는 기술

    - 모호한 질문엔 모호하게 답하기에 정확하게 답하게 하기 위해 필요하다
    - 정확한 출력은 좋은 입력에서 시작되기에 출력 퉐리티 차이가 발생한다

**프롬프트 엔지니어링 구성**
1. 역할
2. 맥락 및 목적
3. 답변 형식


**답변의 다양성과 무작위성 제어**
1. Temperature
- 확률 분포 자체를 날카롭게(0) 혹은 넓게(1) 조절
    - 0 : 일관되고 예측 가능한 결과
    - 0.3 ~ 0.7 :적당한 창의적 결과
    - 1: 창의적이지만 불안정한 결과

2. top_k
- 확률이 가장 높은 K 개 단어만 후보로 제한

3. top_p
- 누적 확률이 p를 넘기전까지 단어를 모아 후보로 삼음


### ChatPromptTemplate
- 시스템 메시지, 사용자 메시지, AI 메시지 등 역할을 구분하는 템플릿
- 다중 메시지 기반의 프롬프트 흐름 구성을 도와주는 템플릿

**메시지 종류**
1. SystemMessage : AI 역할
2. HummanMessage : 사용자 메시지
3. AIMessage : AI 응답


---
## Output Parser
- 텍스트를 구조화된 데이터로 가공해주는 도구

    - 분석을 위해, 출력값을 조정하기 위해 구조화된 데이터가 필요하기 때문

### CommaSeparatedOutputParser
- 쉼표 구분 문자열을 리스트로 변환

### StructuredOutputParser
- JSON 기반 구조화 파싱

### pydanticOutputParser
- 텍스트를 Pydantic 모델로 파싱
    -**Pydantic** : 데이터를 정희하고 검증할 수 있는 파이썬
    - python 객체처럼 쓰면서도, JSON -> python 변환에 아주 유리함

    
---
## Memory
- 대화의 맥락을 이어가기 위해, 이전 대화를 저장하는 것

### conversationBufferMemory
- 모든 대화를 순차적으로 저장
- 대화 길이가 짧고, 단순한 맥락 유지에 적합

### conversationSummaryMemory
- 대화를 요약해서 저장
- 긴 대화, 리로스 절약이 필요할 때 적합

### conversationBurrerWindowMemory
- 최근 N턴만
- 최신 문맥만 중요할 때