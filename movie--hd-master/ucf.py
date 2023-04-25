#!/usr/bin/env python
# coding: utf-8

# In[ ]:





# In[1]:


import pandas as pd
import numpy as np
from sklearn.metrics.pairwise import cosine_similarity

from sqlalchemy import create_engine


# In[2]:



engine = create_engine("mysql+pymysql://root:du197312299@localhost:3306/film?charset=utf8")


# In[3]:


ratings = pd.read_sql('select * from ratings', engine.connect())
ratings = ratings.set_index('indexl')
ratings.head()


# In[4]:


movies = pd.read_sql('select * from movies', engine.connect())
movies = movies.set_index('index')
movies.head()


# In[5]:


df = pd.merge(ratings, movies, on='movieId', how='inner')
df.head()# Merge ratings and movies datasets


# In[6]:


agg_ratings = df.groupby('movieId').agg(mean=('rating', 'mean'), num=('rating', 'count')).reset_index()
agg_ratings_gt_100 = agg_ratings[agg_ratings['num'] > 1]
agg_ratings_gt_100.sort_values(by='num', ascending=False)
agg_ratings_gt_100.info()#keep only the movies with greater than 100 ratings.


# In[7]:


train_data = pd.merge(df, agg_ratings_gt_100[['movieId']], on='movieId', how='inner')
train_data.head()
#To keep only the 134 movies with more than 100 ratings, we need to join the movie with the user-rating level dataframe.


# In[8]:


matrix = train_data.pivot_table(index='userId', columns='movieId', values='rating')
matrix.head()# Create user-item matrix


# In[9]:


matrix_norm = matrix.subtract(matrix.mean(axis=1), axis = 'rows')
matrix_norm.head()# Normalize user-item matrix


# In[10]:


user_similarity = matrix_norm.T.corr()
user_similarity.head(570)# User similarity matrix using Pearson correlation


# In[11]:


ids, me, sims = [], [], []
idx = 1
for index, row in user_similarity.iterrows():
    me.append(index)
    ids.append(idx)
    idx += 1
    sim = ""
    for col in user_similarity.columns:
        if col == index:
            continue
        if(not np.isnan(row[col])):
            sim += str(col) + ":" + str(row[col]) + ","
    if len(sim) > 0:
        sim = sim[:-1]
    sims.append(sim)
    
df = pd.DataFrame()

df['id'] = ids
df['sim_user'] = sims
df['user_id'] = me

df.to_sql("sim_user", engine, schema="film", if_exists='replace', index=False, chunksize=None, dtype=None)


# In[12]:


ids, user, movies, ratings = [], [], [], []
idx = 1
for index, row in matrix_norm.iterrows():
    user_id = int(index)
    for col in matrix_norm.columns:
        if(not np.isnan(row[col])):
            m_id = int(col)
            rating = float(row[col])
            ids.append(idx)
            idx += 1
            user.append(user_id)
            movies.append(m_id)
            ratings.append(rating)
    
df = pd.DataFrame()

df['id'] = ids
df['movie_id'] = movies
df['rating'] = ratings
df['user_id'] = user

df.to_sql("ratings_norm", engine, schema="film", if_exists='replace', index=False, chunksize=None, dtype=None)


# In[ ]:




