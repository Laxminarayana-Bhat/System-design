package org.example.ratelimiter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RateLimiter implements Filter {

    private final TokenBucket tokenBucket;

    public RateLimiter(TokenBucket tokenBucket) {
        this.tokenBucket = tokenBucket;
    }
    //| Algorithm      | Description               | Pros           | Cons                  |
    //| -------------- | ------------------------- | -------------- | --------------------- |
    //| Fixed Window   | Count per time window     | Simple         | Burst at window edges |
    //| Sliding Window | Rolling time window       | Accurate       | More storage          |
    //| Token Bucket   | Tokens refilled over time | Smooth traffic | Slightly complex      |
    //| Leaky Bucket   | Constant outflow          | Stable         | Can drop bursts       |
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//    Its for api request filtering for jwt etc.
//    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String clientId = servletRequest.getRemoteAddr();
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;//parent of http sR is sR


        if (!tokenBucket.allowRequest(clientId)) {
            httpResponse.setContentType("text/plain");
            httpResponse.setStatus(429);
            httpResponse.getWriter().write("Too many requests");
            return;
        }

        filterChain.doFilter(servletRequest, httpResponse);
    }

}
