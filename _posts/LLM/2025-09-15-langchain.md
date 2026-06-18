---
title: langchain
date: 2026-04-27 10:00:00 +0900
categories: [LLM, Framework]
tags: [ai, llm, gpt, prompt, langchain]
---

# LangChain

## 1. 환경구축
### 라이브러리 임포트
```
!pip install langchain langchain-openai langchain_community -q

import pandas as pd
import numpy as np
import os
import openai

from langchain.prompts import PromptTemplate
from langchain.prompts.chat import ChatPromptTemplate
from langchain_openai import ChatOpenAI
from langchain.chains import LLMChain
from langchain.schema import HumanMessage, SystemMessage, AIMessage

import warnings
warnings.filterwarnings("ignore", category=DeprecationWarning)
```


### OpenAI key 등록
```
def load_api_keys(filepath="api_key.txt"):
    with open(filepath, "r") as f:
        for line in f:
            line = line.strip()
            if line and "=" in line:
                key, value = line.split("=", 1)
                os.environ[key.strip()] = value.strip()

path = 

# API 키 로드 및 환경변수 설정
load_api_keys(path + '.txt')
```


---
## Model
```
# 모델 선언
chat = ChatOpenAI(model = "gpt-4.1-mini")

# 역할부여
sys_role = 
question = 

result = chat.invoke([HumanMessage(content = question), SystemMessage(content = sys_role)])
result
```

---
## ChatPromptTemplate
```
# 메시지
s_msg = 
h_msg = 

# 프롬프트 템플릿
chat_prompt = ChatPromptTemplate.from_messages([
    ("system", s_msg),
    ("human", h_msg),
])

# 모델에 적용
llm = ChatOpenAI(model_name = 'gpt-4.1-mini',
                 temperature=1, top_p = 0.95)

# 실제 메시지 객체 리스트를 생성
messages = chat_prompt.format_messages()

# 생성
response = llm.invoke(messages)
print(response.content)
```

---
## OutputParser
```
# 출력파서 임포트
from pydantic import BaseModel
from langchain.output_parsers import PydanticOutputParser

# 1. Pydantic 모델 정의
class BookInfo(BaseModel):
    title: 
    author: 
    year: 

# 2. 파서 생성
parser = PydanticOutputParser(pydantic_object=BookInfo)

# 3. 프롬프트 구성 (ChatPromptTemplate 사용)
prompt = ChatPromptTemplate.from_messages([
    ("system", "."),
    ("human", "."),
    ("system", "{}")  
])

# 4. 메시지 생성
messages = prompt.format_messages(
    format_instructions=parser.get_format_instructions()
)

# 5. LLM 호출 및 파싱
llm = ChatOpenAI(model_name = 'gpt-4.1-mini', temperature=0.5, model_kwargs={"top_p": 0.95})
response = llm.invoke(messages)
book = parser.parse(response.content)

# 6. 결과 출력
print(book)
```

---
## Memory
```
# 메모리 생성
from langchain.chains import ConversationChain
from langchain.memory import ConversationBufferMemory # 버퍼
from langchain.memory import ConversationSummaryMemory # 요약
from langchain.memory import ConversationBufferWindowMemory # n턴

# 메모리 포함 체인 생성
memory = ConversationBufferMemory()
memory = ConversationSummaryMemory()
memory = ConversationBufferWindowMemory(2)
chain = ConversationChain(llm = llm, memory=memory)

# 대화 시작
print(chain.run("안녕? 나는 기영이야."))
```