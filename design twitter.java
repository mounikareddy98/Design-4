//TC:O(1)
SC:O(no.of users) - for the maps I used

//used to maps for connecting the users with his followers and the users with their tweets.
Retrieved the required tweets by searching for the users over the map. For the followers of an user a hashset is used to avoid duplication of his followers.

class Twitter {
    class Tweet{
        int Tweetid;
        int createdAt;
        public Tweet(int Tweetid,int createdAt){
            this.Tweetid = Tweetid ;
            this.createdAt = createdAt;
        }
    }
    HashMap<Integer,HashSet>follow;
    HashMap<Integer,List<Tweet>>tweets;
    public Twitter() {
        this.follow = new HashMap<>();
        this.tweets = new HashMap<>();
    }
    int time;
    public void postTweet(int userId, int tweetId) {
        follow(userId,userId);
        if(!tweets.containsKey(userId)){
            tweets.put(userId,new ArrayList<>());
        }
        tweets.get(userId).add(new Tweet(tweetId,time++));
    }
    
    public List<Integer> getNewsFeed(int userId) {
        List<Integer>result = new ArrayList<>();
       
        PriorityQueue<Tweet>pq = new PriorityQueue<>((a,b)->a.createdAt-b.createdAt);
        Set<Integer>followers = follow.get(userId);
        if(followers!=null){
            for(int fid: followers){
            if(follow.containsKey(fid)){
                List<Tweet>t1 = tweets.get(fid);
                for(Tweet t2: t1){
                    pq.add(t2);
                    if(pq.size() > 10){
                        pq.poll();
                    }
                }
            }
           
        }
        }
        
        while(!pq.isEmpty()){
           result.add(0,pq.poll().Tweetid);
        }
        return result;
    }
    
    public void follow(int followerId, int followeeId) {
        if(!follow.containsKey(followerId)){
            follow.put(followerId,new HashSet<>());
        }
        follow.get(followerId).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if(followeeId == followerId && !follow.containsKey(followerId)){
            return;
        }
        follow.get(followerId).remove(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */