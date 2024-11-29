class Solution {
    public String simplifyPath(String path) {
        String[] components = path.split("/");

        List<String> fileList = new ArrayList<>();

        for(String part: components) {
            if (part.length() == 0 || part.equals(".") || part.equals("/"))
                continue;
            else if (part.equals("..")) {
                if (!fileList.isEmpty()) {
                    fileList.remove(fileList.size() - 1);
                }
            }
            else {
                fileList.add(part);
            }
        }

        if (fileList.isEmpty())
            return "/";

        StringBuilder finalPath = new StringBuilder();
        for(String s : fileList)
            finalPath.append("/").append(s);
        
        return finalPath.toString();
    }
}